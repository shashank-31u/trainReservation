package com.casestudy.train.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.train.dto.CoachDTO;
import com.casestudy.train.dto.ResponseDTO;
import com.casestudy.train.dto.SeatDTO;
import com.casestudy.train.dto.StationDTO;
import com.casestudy.train.dto.TrainInboundDTO;
import com.casestudy.train.dto.TrainOutboundDTO;
import com.casestudy.train.interfaces.TrainService;
import com.casestudy.train.model.Coach;
import com.casestudy.train.model.Seat;
import com.casestudy.train.model.Station;
import com.casestudy.train.model.Train;
import com.casestudy.train.model.TrainCounter;
import com.casestudy.train.repository.CoachRepository;
import com.casestudy.train.repository.SeatRepository;
import com.casestudy.train.repository.StationRepository;
import com.casestudy.train.repository.TrainCounterRepository;
import com.casestudy.train.repository.TrainRepository;
import com.casestudy.train.utils.StringUtils;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainCounterRepository trainCounterRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public TrainOutboundDTO addTrainInfo(TrainInboundDTO trainInboundDTODetails) {

        try {
            List<Train> trainList = trainRepository.findByTrainName(trainInboundDTODetails.getTrainName());
            if (trainList != null && trainList.size() > 0) {
                return null;
            }

            Train train = new Train();
            train.setTrainName(trainInboundDTODetails.getTrainName());
            train.setTrainType(trainInboundDTODetails.getTrainType());

            List<StationDTO> sourceStations = trainInboundDTODetails.getStationDTO().stream()
                    .filter(station -> Boolean.TRUE.equals(station.getIsSource())).collect(Collectors.toList());
            if (sourceStations != null && !sourceStations.isEmpty()) {
                train.setSource(sourceStations.get(0).getCity());
            } else {
                train.setSource(trainInboundDTODetails.getStationDTO().get(0).getCity());
            }

            List<StationDTO> destinationStations = trainInboundDTODetails.getStationDTO().stream()
                    .filter(station -> Boolean.TRUE.equals(station.getIsDestination())).collect(Collectors.toList());
            if (destinationStations != null && !destinationStations.isEmpty()) {
                train.setDestination(destinationStations.get(0).getCity());
            } else {
                int size = trainInboundDTODetails.getStationDTO().size();
                train.setDestination(trainInboundDTODetails.getStationDTO().get(size - 1).getCity());
            }

            train.setScheduledDay(trainInboundDTODetails.getScheduledDays());
            train.setTotalDistance(trainInboundDTODetails.getTotalDistance());

            TrainCounter trainNumberCounter = trainCounterRepository.findTrainCounterValue();
            Long counterValue;
            if (trainNumberCounter == null || trainNumberCounter.getCounter() == 0L) {
                TrainCounter trainCounter = new TrainCounter();
                counterValue = 10001L;
                trainCounter.setCounter(counterValue);
                trainCounterRepository.save(trainCounter);

            } else {
                counterValue = trainNumberCounter.getCounter() + 1;
                trainNumberCounter.setCounter(counterValue);
                trainCounterRepository.save(trainNumberCounter);
            }
            train.setTrainNumber(counterValue.toString());
            train = trainRepository.save(train);
            addCoaches(trainInboundDTODetails, train);
            addStations(trainInboundDTODetails, train);
            return createOutboundJson(train);

        } catch (Exception e) {
            e.printStackTrace();
            return createOutboundJson(new Train());
        }

    }

    private void addCoaches(TrainInboundDTO trainInboundDTODetails, Train train) {

        if (trainInboundDTODetails.getCoachDTO() != null && trainInboundDTODetails.getCoachDTO().size() > 0) {

            List<Coach> coachDetailsByDB = coachRepository.findCoachByTrainId(train.getTrainId());

            for (CoachDTO coachDTO : trainInboundDTODetails.getCoachDTO()) {
                Coach coach;
                if (coachDetailsByDB != null && !coachDetailsByDB.isEmpty()) {
                    coach = coachDetailsByDB.stream()
                            .filter(coachDB -> (Objects.equals(coachDTO.getCoachId(), coachDB.getCoachId())
                                    || Objects.equals(coachDTO.getCoachNumber(), coachDB.getCoachNumber())))
                            .findFirst()
                            .orElse(new Coach());
                } else {
                    coach = new Coach();
                }

                coach.setTrainId(train.getTrainId());
                coach.setTrainNumber(train.getTrainNumber());
                coach.setCoachNumber(coachDTO.getCoachNumber());
                coach.setCoachType(coachDTO.getCoachType());
                coach.setCoachNormalFare(coachDTO.getCoachNormalFare());
                coach.setCoachTaktalFare(coachDTO.getCoachTaktalFare());
                coach.setCoachPremiumTaktalFare(coachDTO.getCoachPremiumTaktalFare());
                coach = coachRepository.save(coach);
                addSeats(coachDTO, coach, train);
            }

        }

    }

    private void addSeats(CoachDTO coachDTO, Coach coach, Train train) {

        if (coachDTO != null) {

            List<Seat> seatDetailsByDB = seatRepository.findSeatsByTrainIdAndCoachId(train.getTrainId(),
                    coach.getCoachId());

            for (SeatDTO seatDTO : coachDTO.getSeats()) {
                Seat seat;
                if (seatDetailsByDB != null & !seatDetailsByDB.isEmpty()) {
                    seat = seatDetailsByDB.stream()
                            .filter(seatDB -> (Objects.equals(seatDTO.getSeatId(), seatDB.getSeatId())
                                    || Objects.equals(seatDTO.getSeatNumber(), seatDB.getSeatNumber())))
                            .findFirst()
                            .orElse(new Seat());
                } else {
                    seat = new Seat();
                }
                seat.setTrainId(train.getTrainId());
                seat.setTrainNumber(train.getTrainNumber());
                seat.setCoachId(coach.getCoachId());
                seat.setCoachNumber(coach.getCoachNumber());
                seat.setIsReserved(false);
                seat.setSeatType(seatDTO.getSeatType());
                seat.setSeatNumber(seatDTO.getSeatNumber());
                seatRepository.save(seat);
            }
        }

    }

    private void addStations(TrainInboundDTO trainInboundDTODetails, Train train) {
        if (trainInboundDTODetails.getStationDTO() != null && !trainInboundDTODetails.getStationDTO().isEmpty()) {

            List<Station> stationDetailsByDB = stationRepository.findStationsByTrainId(train.getTrainId());

            for (StationDTO stationDTO : trainInboundDTODetails.getStationDTO()) {
                Station station;
                if (stationDetailsByDB != null && !stationDetailsByDB.isEmpty()) {
                    station = stationDetailsByDB.stream()
                            .filter(stationDB -> (Objects.equals(stationDTO.getStationId(), stationDB.getStationId())
                                    || (Objects.equals(stationDTO.getStationName(), stationDB.getStationName())
                                            && Objects.equals(stationDTO.getCity(), stationDB.getCity()))))
                            .findFirst().orElse(new Station());
                } else {
                    station = new Station();
                }
                station.setStationName(stationDTO.getStationName());
                station.setCity(stationDTO.getCity());
                station.setState(stationDTO.getState());
                station.setNoOfPlatform(stationDTO.getNoOfPlatform());
                station.setTimeOfArrival(stationDTO.getTimeOfArrival());
                station.setTimeOfDeparture(stationDTO.getTimeOfDeparture());
                station.setTrainId(train.getTrainId());
                station.setTrainName(train.getTrainName());
                station.setSource(train.getSource());
                station.setDestination(train.getDestination());
                stationRepository.save(station);
            }
        }
    }

    private TrainOutboundDTO createOutboundJson(Train train) {
        TrainOutboundDTO trainOutboundDTO = new TrainOutboundDTO();

        if (train != null && train.getTrainId() != null && train.getTrainId() != 0L) {

            trainOutboundDTO.setTrainId(train.getTrainId());
            trainOutboundDTO.setTrainNumber(train.getTrainNumber());
            trainOutboundDTO.setSource(train.getSource());
            trainOutboundDTO.setDestination(train.getDestination());
            trainOutboundDTO.setTotalDistance(train.getTotalDistance());
            trainOutboundDTO.setTrainType(train.getTrainType());
            trainOutboundDTO.setScheduledDays(train.getScheduledDay());
        }

        return trainOutboundDTO;

    }

    @Override
    public Train getTrainDetailsByTrainNumber(String trainNumber) {

        List<Train> trainLists = trainRepository.findByTrainNumber(trainNumber);
        if (trainLists == null || trainLists.isEmpty()) {
            return null;
        }

        Train train = trainLists.get(0);
        train.setCoachList(getCoachesByTrainId(train));
        train.setStationList(getStationsByTrainId(train));

        return train;
    }

    public List<Coach> getCoachesByTrainId(Train train) {

        List<Coach> coachLists = coachRepository.findCoachByTrainId(train.getTrainId());
        if (coachLists != null && !coachLists.isEmpty()) {
            for (Coach coach : coachLists) {
                coach.setSeat(getSeatsByTrainIdAndCoachId(train, coach));
            }
        }
        return coachLists;

    }

    public List<Seat> getSeatsByTrainIdAndCoachId(Train train, Coach coach) {
        List<Seat> seatLists = seatRepository.findSeatsByTrainIdAndCoachId(train.getTrainId(), coach.getCoachId());
        return seatLists;
    }

    public List<Station> getStationsByTrainId(Train train) {
        List<Station> stationLists = stationRepository.findStationsByTrainId(train.getTrainId());
        return stationLists;
    }

    @Override
    public List<Train> getAllTrainDetails() {

        List<Train> trains = new ArrayList<>();
        trains = trainRepository.findAll();
        for (Train train : trains) {
            train.setCoachList(getCoachesByTrainId(train));
            train.setStationList(getStationsByTrainId(train));
        }

        return trains;
    }

    @Override
    public TrainOutboundDTO updateTrainInfo(TrainInboundDTO trainInboundDTODetails) {

        try {
            if (!StringUtils.checkNullString(trainInboundDTODetails.getTrainNumber()).isEmpty()) {
                List<Train> trainDetails = trainRepository.findByTrainNumber(trainInboundDTODetails.getTrainNumber());

                if (trainDetails != null && !trainDetails.isEmpty()) {
                    Train train = trainDetails.get(0);
                    train.setTrainName(trainInboundDTODetails.getTrainName());
                    train.setTrainType(trainInboundDTODetails.getTrainType());
                    train.setSource(trainInboundDTODetails.getSourceCity());
                    train.setDestination(trainInboundDTODetails.getDestinationCity());
                    train.setScheduledDay(trainInboundDTODetails.getScheduledDays());
                    train.setTotalDistance(trainInboundDTODetails.getTotalDistance());
                    addCoaches(trainInboundDTODetails, train);
                    addStations(trainInboundDTODetails, train);
                    trainRepository.save(train);
                    return createOutboundJson(train);
                }
            }
            return createOutboundJson(new Train());
        } catch (Exception e) {
            e.printStackTrace();
            return createOutboundJson(new Train());
        }
    }

    @Override
    public ResponseDTO deleteTrainInfo(TrainInboundDTO trainInboundDTODetails) {

        try {
            if (!StringUtils.checkNullString(trainInboundDTODetails.getTrainNumber()).isEmpty()) {
                List<Train> trainDetails = trainRepository.findByTrainNumber(trainInboundDTODetails.getTrainNumber());
                if (trainDetails != null && !trainDetails.isEmpty()) {
                    Train train = trainDetails.get(0);
                    List<Coach> coachLists = getCoachesByTrainId(train);
                    List<Station> stationLists = getStationsByTrainId(train);

                    if (coachLists != null && !coachLists.isEmpty()) {
                        for (Coach coach : coachLists) {
                            List<Seat> seatLists = getSeatsByTrainIdAndCoachId(train, coach);
                            if (seatLists != null && !seatLists.isEmpty()) {
                                List<Long> seatIds = seatLists.stream()
                                        .map(Seat::getSeatId)
                                        .collect(Collectors.toList());
                                seatRepository.deleteAllById(seatIds);
                            }
                            List<Long> coachIds = coachLists.stream().map(Coach::getCoachId)
                                    .collect(Collectors.toList());
                            coachRepository.deleteAllById(coachIds);
                        }
                    }

                    if (stationLists != null && !stationLists.isEmpty()) {
                        List<Long> stationIds = stationLists.stream().map(Station::getStationId)
                                .collect(Collectors.toList());
                        stationRepository.deleteAllById(stationIds);
                    }

                    trainRepository.delete(train);

                    return new ResponseDTO("SUCCESS", null, LocalDateTime.now(), true,
                            "Train Record Deleted Successfully!");
                } else {
                    return new ResponseDTO("SUCCESS", null, LocalDateTime.now(), true,
                            "Train Doesn't Exists With Train Number: " + trainInboundDTODetails.getTrainNumber());
                }
            }
            return new ResponseDTO("SUCCESS", null, LocalDateTime.now(), true,
                    "Train Number Not Provided!");

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("FAILED!");
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Updating The Record!"));
            return responseDTO;
        }

    }

}
