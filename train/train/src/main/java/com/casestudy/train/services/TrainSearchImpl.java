package com.casestudy.train.services;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.train.dto.TrainSearchDTO;
import com.casestudy.train.interfaces.TrainSearchService;
import com.casestudy.train.model.Coach;
import com.casestudy.train.model.Seat;
import com.casestudy.train.model.Station;
import com.casestudy.train.model.Train;
import com.casestudy.train.model.TrainSchedule;
import com.casestudy.train.repository.StationRepository;
import com.casestudy.train.repository.TrainRepository;
import com.casestudy.train.repository.TrainScheduleRepository;
import com.casestudy.train.utils.StringConstants;
import com.casestudy.train.utils.StringUtils;

@Service
public class TrainSearchImpl implements TrainSearchService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;

    @Autowired
    private TrainServiceImpl trainServiceImpl;

    @Override
    public List<TrainSchedule> getTrainSchedule(TrainSearchDTO trainSearchDTO) {

        List<Long> getAllTrainIds = new ArrayList<>();
        List<TrainSchedule> getAllTrainSchedule = new ArrayList<>();
        if (trainSearchDTO != null && !StringUtils.checkNullString(trainSearchDTO.getDestinationCity()).isEmpty()
                && !StringUtils.checkNullString(trainSearchDTO.getSourceCity()).isEmpty()) {
            List<Station> trainListFromSource = stationRepository.findStationsByCity(trainSearchDTO.getSourceCity());
            List<Station> trainListFromDestination = stationRepository
                    .findStationsByCity(trainSearchDTO.getDestinationCity());

            if (trainListFromSource != null && !trainListFromSource.isEmpty() && trainListFromDestination != null
                    && !trainListFromDestination.isEmpty()) {
                getAllTrainIds = trainListFromSource.stream()
                        .filter(source -> trainListFromDestination.stream().anyMatch(
                                destination -> !Objects.equals(source.getStationId(), destination.getStationId())))
                        .map(Station::getTrainId)
                        .distinct()
                        .collect(Collectors.toList());
            }

            if (getAllTrainIds != null && getAllTrainIds.size() > 0) {
                try {
                    List<Train> getAllTrains = trainRepository.findAllById(getAllTrainIds);
                    if (getAllTrains != null && !getAllTrains.isEmpty()) {
                        LocalDateTime journeyDate = trainSearchDTO.getDateOfJourney().toLocalDateTime();
                        DayOfWeek dayOfWeek = journeyDate.getDayOfWeek();

                        getAllTrains = getAllTrains.stream()
                                .filter(train -> train.getScheduledDay().stream()
                                        .anyMatch(weekday -> weekday.equalsIgnoreCase(dayOfWeek.toString())))
                                .collect(Collectors.toList());
                        if (getAllTrains == null || getAllTrains.isEmpty()) {
                            return getAllTrainSchedule;
                        } else if (getAllTrains != null && !getAllTrains.isEmpty()) {
                            for (Train train : getAllTrains) {
                                TrainSchedule trainSchedule = new TrainSchedule();
                                trainSchedule.setTrainId(train.getTrainId());
                                trainSchedule.setTrainNumber(train.getTrainNumber());
                                trainSchedule.setTrainName(train.getTrainName());
                                trainSchedule.setDateOfJourney(trainSearchDTO.getDateOfJourney());
                                trainSchedule.setDayOfJourney(dayOfWeek.toString());
                                trainSchedule.setTrainSource(train.getSource());
                                trainSchedule.setTrainDestination(train.getDestination());

                                List<Station> stationList = trainServiceImpl.getStationsByTrainId(train);
                                /* SOURCE DETAILS */
                                Station sourceStation = stationList.stream()
                                        .filter(station -> trainSearchDTO.getSourceCity()
                                                .equals(station.getCity()))
                                        .findFirst()
                                        .orElse(null);
                                if (sourceStation != null) {
                                    trainSchedule.setSourceStationId(sourceStation.getStationId());
                                    trainSchedule.setSourceStationName(sourceStation.getStationName());
                                    trainSchedule.setSourceCity(sourceStation.getCity());
                                    trainSchedule.setSourceState(sourceStation.getState());
                                    trainSchedule.setSourceTimeOfArrival(sourceStation.getTimeOfArrival());
                                    trainSchedule.setSourceTimeOfDeparture(sourceStation.getTimeOfDeparture());

                                    Time departureTime = sourceStation.getTimeOfDeparture();
                                    Time arrivalTime = sourceStation.getTimeOfArrival();

                                    // Convert java.sql.Time to java.util.Date for arithmetic operations
                                    long departureMillis = departureTime.getTime();
                                    long arrivalMillis = arrivalTime.getTime();

                                    // Calculate the time difference in milliseconds
                                    long timeDifferenceMillis = departureMillis - arrivalMillis;
                                    trainSchedule.setSourceHalt(timeDifferenceMillis);
                                    trainSchedule.setSourcePlatfromNumber(0);
                                }

                                /* DESTINATION DETAILS */
                                Station destinationStation = stationList.stream()
                                        .filter(station -> trainSearchDTO.getDestinationCity()
                                                .equals(station.getCity()))
                                        .findFirst()
                                        .orElse(null);
                                if (destinationStation != null) {
                                    trainSchedule.setDestinationStationId(destinationStation.getStationId());
                                    trainSchedule.setDestinationStationName(destinationStation.getStationName());
                                    trainSchedule.setDestinationCity(destinationStation.getCity());
                                    trainSchedule.setDestinationState(destinationStation.getState());
                                    trainSchedule.setDestinationTimeOfArrival(destinationStation.getTimeOfArrival());
                                    trainSchedule
                                            .setDestinationTimeOfDeparture(destinationStation.getTimeOfDeparture());

                                    Time departureTime = destinationStation.getTimeOfDeparture();
                                    Time arrivalTime = destinationStation.getTimeOfArrival();

                                    // Convert java.sql.Time to java.util.Date for arithmetic operations
                                    long departureMillis = departureTime.getTime();
                                    long arrivalMillis = arrivalTime.getTime();

                                    // Calculate the time difference in milliseconds
                                    long timeDifferenceMillis = departureMillis - arrivalMillis;

                                    trainSchedule.setDestinationHalt(timeDifferenceMillis);
                                    trainSchedule.setDestinationPlatfromNumber(0);
                                }

                                /* SEAT AVAILABILITY */
                                trainSchedule.setSeatsAvailable(seatsAvailabilityPerTrain(train));

                                /** IS DAILY */
                                Boolean isDaily = StringConstants.weekdays.stream().map(String::toLowerCase)
                                        .allMatch(train.getScheduledDay()::contains)
                                        && train.getScheduledDay().stream().map(String::toLowerCase)
                                                .allMatch(StringConstants.weekdays::contains);
                                trainSchedule.setIsDaily(isDaily);
                                getAllTrainSchedule.add(trainSchedule);
                            }
                            trainScheduleRepository.saveAll(getAllTrainSchedule);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getAllTrainSchedule;

    }

    public Long seatsAvailabilityPerTrain(Train train) {

        Long seatAvailable = 0L;
        List<Coach> coachLists = trainServiceImpl.getCoachesByTrainId(train);
        if (coachLists != null && !coachLists.isEmpty()) {
            for (Coach coach : coachLists) {
                List<Seat> seatLists = trainServiceImpl.getSeatsByTrainIdAndCoachId(train, coach);
                seatLists = seatLists.stream().filter(seat -> !seat.getIsReserved()).collect(Collectors.toList());
                if (seatLists != null && !seatLists.isEmpty()) {
                    seatAvailable += seatLists.size();
                }
            }
        }

        return seatAvailable;

    }

}
