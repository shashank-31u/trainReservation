package com.casestudy.train.services;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.train.dto.TrainSearchDTO;
import com.casestudy.train.interfaces.TrainSearchService;
import com.casestudy.train.model.Coach;
import com.casestudy.train.model.Seat;
import com.casestudy.train.model.SeatBooking;
import com.casestudy.train.model.Station;
import com.casestudy.train.model.Train;
import com.casestudy.train.model.TrainFare;
import com.casestudy.train.model.TrainPrice;
import com.casestudy.train.model.TrainSchedule;
import com.casestudy.train.repository.SeatBookingRepository;
import com.casestudy.train.repository.SeatRepository;
import com.casestudy.train.repository.StationRepository;
import com.casestudy.train.repository.TrainFareRepository;
import com.casestudy.train.repository.TrainPriceRepository;
import com.casestudy.train.repository.TrainRepository;
import com.casestudy.train.repository.TrainScheduleRepository;
import com.casestudy.train.utils.PriceConstants;
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

    @Autowired
    private TrainPriceRepository trainPriceRepository;

    @Autowired
    private TrainFareRepository trainFareRepository;

    @Autowired
    private SeatBookingRepository seatBookingRepository;

    @Autowired
    private SeatRepository seatRepository;

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

                                trainSchedule.setDistanceBetweenSourceDestination(
                                        Math.abs(destinationStation.getDistanceBetweenDestination()
                                                - sourceStation.getDistanceBetweenDestination()));
                                /* SEAT AVAILABILITY */
                                trainSchedule.setSeatsAvailable(seatsAvailabilityPerTrain(trainSchedule, train));

                                /** IS DAILY */
                                Boolean isDaily = StringConstants.weekdays.stream().map(String::toLowerCase)
                                        .allMatch(train.getScheduledDay()::contains)
                                        && train.getScheduledDay().stream().map(String::toLowerCase)
                                                .allMatch(StringConstants.weekdays::contains);
                                trainSchedule.setIsDaily(isDaily);
                                getAllTrainSchedule.add(trainSchedule);
                            }
                            List<TrainSchedule> trainSchedules = trainScheduleRepository.saveAll(getAllTrainSchedule);
                            calculateFare(trainSchedules);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getAllTrainSchedule;

    }

    public Long seatsAvailabilityPerTrain(TrainSchedule trainSchedule, Train train) {

        Long seatAvailable = 0L;
        List<SeatBooking> bookings = seatBookingRepository.findByTrainNumber(train.getTrainNumber());
        List<Seat> seatList = seatRepository.findByTrainNumber(train.getTrainNumber());

        if (seatList != null && !seatList.isEmpty() && bookings != null && !bookings.isEmpty()) {

            Set<String> bookedSeatIdentifiers = bookings.stream()
                    .map(booking -> getSeatIdentifier(booking))
                    .collect(Collectors.toSet());

            seatList = seatList.stream()
                    .filter(seat -> !bookedSeatIdentifiers.contains(getSeatIdentifier(seat)))
                    .collect(Collectors.toList());

            if (seatList != null && !seatList.isEmpty()) {
                for (Seat seat : seatList) {
                    SeatBooking seatBooking = new SeatBooking();
                    seatBooking.setSeatId(seat.getSeatId());
                    seatBooking.setSeatNumber(seat.getSeatNumber());
                    seatBooking.setIsReserved(false);
                    seatBooking.setSeatType(seat.getSeatType());
                    seatBooking.setCoachId(seat.getCoachId());
                    seatBooking.setCoachNumber(seat.getCoachNumber());
                    seatBooking.setCoachType(seat.getCoachType());
                    seatBooking.setTrainId(train.getTrainId());
                    seatBooking.setTrainNumber(train.getTrainNumber());
                    seatBookingRepository.save(seatBooking);
                    bookings.add(seatBooking);
                }
            }

        }

        if (bookings == null || bookings.isEmpty()) {
            List<Coach> coachLists = trainServiceImpl.getCoachesByTrainId(train);
            if (coachLists != null && !coachLists.isEmpty()) {
                for (Coach coach : coachLists) {
                    List<Seat> seatLists = trainServiceImpl.getSeatsByTrainIdAndCoachId(train,
                            coach);
                    seatLists = seatLists.stream().filter(seat -> !seat.getIsReserved()).collect(Collectors.toList());
                    if (seatLists != null && !seatLists.isEmpty()) {
                        for (Seat seat : seatLists) {
                            SeatBooking seatBooking = new SeatBooking();
                            seatBooking.setSeatId(seat.getSeatId());
                            seatBooking.setSeatNumber(seat.getSeatNumber());
                            seatBooking.setIsReserved(false);
                            seatBooking.setSeatType(seat.getSeatType());
                            seatBooking.setCoachId(coach.getCoachId());
                            seatBooking.setCoachNumber(coach.getCoachNumber());
                            seatBooking.setCoachType(coach.getCoachType());
                            seatBooking.setTrainId(train.getTrainId());
                            seatBooking.setTrainNumber(train.getTrainNumber());
                            seatBooking.setDateOfJourney(trainSchedule.getDateOfJourney());
                            seatBooking.setDayOfJourney(trainSchedule.getDayOfJourney());
                            seatAvailable++;
                            seatBookingRepository.save(seatBooking);
                        }
                    }
                }
            }

        } else {
            bookings = bookings.stream()
                    .filter(booking -> checkSeatAvailability(booking.getSourceCity(), booking.getDestinationCity(),
                            trainSchedule.getSourceCity(), trainSchedule.getDestinationCity())
                            && !booking.getIsReserved())
                    .collect(Collectors.toList());
            if (bookings != null && bookings.isEmpty()) {
                seatAvailable = Long.valueOf(bookings.size());
            }
        }

        return seatAvailable;

    }

    public void calculateFare(List<TrainSchedule> trainScheduleList) {
        TrainPrice trainPerKMPrice = getTrainPerKmPrice();
        if (trainScheduleList != null && !trainScheduleList.isEmpty()) {
            for (TrainSchedule trainSchedule : trainScheduleList) {
                TrainFare trainFare = new TrainFare();
                trainFare.setTrainScheduleId(trainSchedule.getTrainScheduleId());
                trainFare.setSourceStationId(trainSchedule.getSourceStationId());
                trainFare.setSourceStationName(trainSchedule.getSourceStationName());
                trainFare.setDestinationStationId(trainSchedule.getDestinationStationId());
                trainFare.setDestinationStationName(trainSchedule.getDestinationStationName());
                trainFare.setDistanceBetweenSourceDestination(trainSchedule.getDistanceBetweenSourceDestination());

                Train train = trainRepository.findById(trainSchedule.getTrainId()).get();
                List<Coach> coachList = trainServiceImpl.getCoachesByTrainId(train);
                if (coachList != null && !coachList.isEmpty()) {
                    trainFare.setTrainId(train.getTrainId());
                    trainFare.setTrainName(train.getTrainName());
                    trainFare.setTrainNumber(train.getTrainNumber());
                    for (Coach coach : coachList) {
                        trainFare.setCoachNumber(coach.getCoachNumber());
                        trainFare.setCoachType(coach.getCoachType());
                        /** CALCULATE NORMAL FARE */
                        Long normalFare = coach.getCoachNormalFare() + trainPerKMPrice.getNormalFarePerKM()
                                * trainSchedule.getDistanceBetweenSourceDestination();
                        trainFare.setCoachNormalFare(normalFare);

                        /** CALCULATE TATKAL FARE */
                        Long tatkalFare = coach.getCoachTaktalFare() + trainPerKMPrice.getTatkalFarePerKM()
                                * trainSchedule.getDistanceBetweenSourceDestination();
                        trainFare.setCoachTaktalFare(tatkalFare);

                        /** CALCULATE PREMIUM FARE */
                        Long premiumTatkalFare = coach.getCoachPremiumTaktalFare()
                                + trainPerKMPrice.getPremiumTatkalFarePerKM()
                                        * trainSchedule.getDistanceBetweenSourceDestination();
                        trainFare.setCoachPremiumTaktalFare(premiumTatkalFare);
                    }

                }
                trainFareRepository.save(trainFare);
            }
        }
    }

    public TrainPrice getTrainPerKmPrice() {
        List<TrainPrice> trainPrices = trainPriceRepository.findAll();
        if (trainPrices == null || trainPrices.isEmpty()) {
            TrainPrice trainPrice = new TrainPrice();
            trainPrice.setNormalFarePerKM(PriceConstants.NORMAL_FARE_PER_KM);
            trainPrice.setTatkalFarePerKM(PriceConstants.TATKAL_FARE_PER_KM);
            trainPrice.setPremiumTatkalFarePerKM(PriceConstants.PREMIUM_TATKAL_FARE_PER_KM);
            trainPriceRepository.save(trainPrice);
            trainPrices.add(trainPrice);
        }
        return trainPrices.get(0);
    }

    @Override
    public List<TrainFare> getTrainFareByTrainNumber(TrainSearchDTO trainSearchDTO, String trainNumber) {
        List<TrainFare> trainFares = new ArrayList<>();
        if (trainSearchDTO.getTrainSearchId() != null && trainSearchDTO.getTrainSearchId() != 0L) {
            TrainSchedule trainSchedule = trainScheduleRepository
                    .findByTrainScheduleId(trainSearchDTO.getTrainSearchId());
            if (trainSchedule != null && trainSchedule.getTrainNumber().equalsIgnoreCase(trainNumber)) {
                trainFares = trainFareRepository
                        .findByTrainScheduleIdAndTrainNumber(trainSchedule.getTrainScheduleId(), trainNumber);
            }
        }

        return trainFares;
    }

    private String getSeatIdentifier(SeatBooking booking) {
        return booking.getTrainNumber() + "_" + booking.getCoachNumber() + "_" + booking.getSeatNumber();
    }

    private String getSeatIdentifier(Seat seat) {
        return seat.getTrainNumber() + "_" + seat.getCoachNumber() + "_" + seat.getSeatNumber();
    }

    private Boolean checkSeatAvailability(String bookingSourceCity, String bookingDestinationCity,
            String trainScheduleSourceCity, String trainScheduleDestinationCity) {
        if (StringUtils.checkNullString(bookingSourceCity).isEmpty()
                || StringUtils.checkNullString(bookingDestinationCity).isEmpty()) {
            return true;
        } else if (StringUtils.checkNullString(bookingSourceCity)
                .equalsIgnoreCase(trainScheduleSourceCity)
                && StringUtils.checkNullString(bookingDestinationCity)
                        .equalsIgnoreCase(trainScheduleDestinationCity)) {
            return true;
        }
        return false;
    }

}
