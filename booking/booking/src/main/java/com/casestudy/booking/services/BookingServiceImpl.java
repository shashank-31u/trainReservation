package com.casestudy.booking.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.booking.dto.BookingDTO;
import com.casestudy.booking.dto.BookingOutboundDTO;
import com.casestudy.booking.dto.PassengerDTO;
import com.casestudy.booking.dto.PassengerOutboundDTO;
import com.casestudy.booking.dto.ResponseDTO;
import com.casestudy.booking.dto.SeatDTO;
import com.casestudy.booking.dto.SeatOutboundDTO;
import com.casestudy.booking.interfaces.BookingService;
import com.casestudy.booking.model.Booking;
import com.casestudy.booking.model.Passenger;
import com.casestudy.booking.model.SeatBooking;
import com.casestudy.booking.model.Train;
import com.casestudy.booking.model.TrainFare;
import com.casestudy.booking.model.TrainSchedule;
import com.casestudy.booking.repository.BookingRepository;
import com.casestudy.booking.repository.PassengerRepository;
import com.casestudy.booking.repository.SeatBookingRepository;
import com.casestudy.booking.repository.TrainFareRepository;
import com.casestudy.booking.repository.TrainRepository;
import com.casestudy.booking.repository.TrainScheduleRepository;
import com.casestudy.booking.utils.StringUtils;

@Service
public class BookingServiceImpl implements BookingService {

    private static final String NORMAL = "NORMAL";
    private static final String TATKAL = "TATKAL";
    private static final String PREMIUM_TATKAL = "PREMIUM TATKAL";

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SeatBookingRepository seatBookingRepository;

    @Autowired
    private TrainFareRepository trainFareRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public BookingOutboundDTO createBooking(BookingDTO bookingDTO) {

        Long fare = 0L;
        BookingOutboundDTO bookingOutboundDTO = new BookingOutboundDTO();
        String dateFormated = new SimpleDateFormat("yyyy-MM-dd").format(bookingDTO.getDateOfJourney());
        System.out.println(dateFormated);
        List<TrainSchedule> trainSchedules = trainScheduleRepository.findScheduleByTrainNumber(
                bookingDTO.getTrainNumber(), bookingDTO.getSourceCity(), bookingDTO.getDestinationCity(),
                dateFormated );

        if (trainSchedules == null || trainSchedules.isEmpty()) {
            return new BookingOutboundDTO();
        }
        TrainSchedule trainSchedule = trainSchedules.get(0);
        /** CHECK SEAT AVAILABILITY */
        if (bookingDTO.getPassengerDTOList() != null && !bookingDTO.getPassengerDTOList().isEmpty()) {
            List<SeatBooking> seatBookings = seatBookingRepository
                    .findByTrainId(trainSchedule.getTrainId());
            if (seatBookings != null && !seatBookings.isEmpty()) {
                seatBookings = seatBookings.stream()
                        .filter(seats -> seats.getCoachType().equalsIgnoreCase(bookingDTO.getCoachType())
                                && !seats.getIsReserved())
                        .collect(Collectors.toList());
            }

            if (seatBookings == null || seatBookings.isEmpty()) {
                bookingOutboundDTO
                        .setMessage("No Seats Available! Confirmed Seats Will Be Allotted Incase Of Cancellation");
            } else if (seatBookings.size() < bookingDTO.getPassengerDTOList().size()) {
                bookingOutboundDTO
                        .setMessage("Partial Seats Available! Confirmed Seats Will Be Allotted Incase Of Cancellation");
            } else {
                bookingOutboundDTO.setMessage("Seats Are Available");
            }
        }

        Booking booking = new Booking();

        booking.setTrainId(trainSchedule.getTrainId());
        booking.setTrainName(trainSchedule.getTrainName());
        booking.setTrainNumber(trainSchedule.getTrainNumber());
        booking.setJourneyStartingStationCity(trainSchedule.getSourceCity());
        booking.setJourneyStartingStationName(trainSchedule.getSourceStationName());
        booking.setJourneyEndingStationCity(trainSchedule.getDestinationCity());
        booking.setJourneyEndingStationName(trainSchedule.getDestinationState());
        booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
        booking.setBookingStatus("AWAITING PAYMENT");
        booking.setSourceStationId(trainSchedule.getSourceStationId());
        booking.setDestinationStationId(trainSchedule.getDestinationStationId());
        booking.setSourceState(trainSchedule.getSourceState());
        booking.setDestinationState(trainSchedule.getDestinationState());
        booking.setBookingType(bookingDTO.getBookingType());
        booking.setDateOfJourney(trainSchedule.getDateOfJourney());
        booking.setDayOfJourney(trainSchedule.getDayOfJourney());
        booking = bookingRepository.save(booking);

        if (bookingDTO.getPassengerDTOList() != null && !bookingDTO.getPassengerDTOList().isEmpty()) {

            for (PassengerDTO passengerDTO : bookingDTO.getPassengerDTOList()) {
                Passenger passenger = new Passenger();
                passenger.setPassengerName(passengerDTO.getPassengerName());
                passenger.setPassengerAge(passengerDTO.getPassengerAge());
                passenger.setGender(passengerDTO.getGender());
                passenger.setBookingId(booking.getBookingId());
                passenger.setBookingStatus("AWAITING PAYMENT");
                passenger.setCoachType(bookingDTO.getCoachType());
                passenger.setDayOfJourney(trainSchedule.getDayOfJourney());
                passenger.setDateOfJourney(trainSchedule.getDateOfJourney());
                passenger.setTrainId(trainSchedule.getTrainId());
                passenger.setTrainName(trainSchedule.getTrainName());
                passenger.setTrainNumber(trainSchedule.getTrainNumber());
                passenger.setJourneyStartingStationCity(trainSchedule.getSourceCity());
                passenger.setJourneyStartingStationName(trainSchedule.getSourceStationName());
                passenger.setJourneyEndingStationCity(trainSchedule.getDestinationCity());
                passenger.setJourneyEndingStationName(trainSchedule.getDestinationState());
                passengerRepository.save(passenger);
            }

            List<TrainFare> trainFareList = trainFareRepository.findByTrainScheduleIdAndTrainNumber(
                    trainSchedule.getTrainScheduleId(), trainSchedule.getTrainNumber());

            if (trainFareList != null && !trainFareList.isEmpty()) {
                trainFareList = trainFareList.stream()
                        .filter(trainFare -> trainFare.getCoachType().equalsIgnoreCase(bookingDTO.getCoachType()))
                        .collect(Collectors.toList());
            }

            if (trainFareList != null && !trainFareList.isEmpty()) {
                TrainFare trainFare = trainFareList.get(0);
                if (bookingDTO.getBookingType().equalsIgnoreCase(NORMAL)) {
                    fare = trainFare.getCoachNormalFare() * bookingDTO.getPassengerDTOList().size();
                } else if (bookingDTO.getBookingType().equalsIgnoreCase(TATKAL)) {
                    fare = trainFare.getCoachTaktalFare() * bookingDTO.getPassengerDTOList().size();
                } else {
                    fare = trainFare.getCoachPremiumTaktalFare() * bookingDTO.getPassengerDTOList().size();
                }

            }

        }
        bookingOutboundDTO.setFare(fare);
        bookingOutboundDTO.setBookingType(bookingDTO.getBookingType());
        bookingOutboundDTO.setBookingId(booking.getBookingId());
        bookingOutboundDTO.setBookingStatus(booking.getBookingStatus());

        return bookingOutboundDTO;

    }

    @Override
    public SeatOutboundDTO saveBooking(BookingDTO bookingDTO) {

        if (bookingDTO.getBookingId() == null) {
            return new SeatOutboundDTO();
        }

        SeatOutboundDTO seatOutboundDTO = new SeatOutboundDTO();
        Booking booking = bookingRepository.findByBookingId(bookingDTO.getBookingId());
        if (booking == null) {
            return new SeatOutboundDTO();
        }

        if (!StringUtils.checkNullString(booking.getBookingStatus()).equalsIgnoreCase("AWAITING PAYMENT")) {
            SeatOutboundDTO seatOutboundDTOResponse = new SeatOutboundDTO();
            List<Passenger> passengersList = passengerRepository.findByBookingId(bookingDTO.getBookingId());
            prepareOutBoundDTOObject(booking, passengersList);
            seatOutboundDTOResponse.setMessage("Booking Already Done!");
            return seatOutboundDTOResponse;

        }

        List<Train> train = trainRepository.findByTrainNumber(booking.getTrainNumber());
        if (booking != null) {
            List<Passenger> passengersList = passengerRepository.findByBookingId(bookingDTO.getBookingId());
            String pnr = StringUtils.generatePnr(booking.getBookingId().toString(), 15);
            for (Passenger passenger : passengersList) {
                seatAllocation(booking, passenger, train);
                passenger.setPnr(pnr);
                passengerRepository.save(passenger);
            }

            booking.setPnr(pnr);
            booking.setPassengersList(passengersList);
            booking.setBookingStatus(bookingStatus(passengersList));
            booking = bookingRepository.save(booking);
            seatOutboundDTO = prepareOutBoundDTOObject(booking, passengersList);
        }
        return seatOutboundDTO;

    }

    public void seatAllocation(Booking booking, Passenger passenger, List<Train> trainList) {

        Train train = trainList.get(0);
        List<SeatBooking> seatBookingList = seatBookingRepository.findByTrainId(train.getTrainId());
        if (seatBookingList != null && !seatBookingList.isEmpty()) {
            seatBookingList = seatBookingList.stream()
                    .filter(seats -> seats.getCoachType().equalsIgnoreCase(passenger.getCoachType())
                            && !seats.getIsReserved())
                    .collect(Collectors.toList());
        }

        if (seatBookingList != null && !seatBookingList.isEmpty() && seatBookingList.size() > 0) {
            SeatBooking seatBooking = seatBookingList.get(0);
            passenger.setSeatId(seatBooking.getSeatId());
            passenger.setBookingStatus("CONFIRMED");
            passenger.setSeatNumber(seatBooking.getSeatNumber());
            passenger.setCoachNumber(seatBooking.getCoachNumber());
            passenger.setSeatType(seatBooking.getSeatType());
            passenger.setCoachId(seatBooking.getCoachId());

            seatBooking.setIsReserved(true);
            seatBooking.setSourceCity(booking.getJourneyStartingStationCity());
            seatBooking.setDestinationCity(booking.getJourneyEndingStationCity());
            seatBooking.setSourceState(booking.getSourceState());
            seatBooking.setDestinationState(booking.getDestinationState());
            seatBookingRepository.save(seatBooking);
        } else {
            passenger.setBookingStatus("WAITING");
        }

    }

    public String bookingStatus(List<Passenger> passengersList) {
        if (passengersList != null && !passengersList.isEmpty()) {
            boolean allConfirmed = passengersList.stream()
                    .allMatch(passenger -> ("CONFIRMED".equalsIgnoreCase(passenger.getBookingStatus()))
                            || ("WAITING".equalsIgnoreCase(passenger.getBookingStatus())));

            boolean anyWaiting = passengersList.stream()
                    .anyMatch(passenger -> "WAITING".equalsIgnoreCase(passenger.getBookingStatus()));

            if (allConfirmed) {
                return passengersList.get(0).getBookingStatus().toUpperCase();
            } else if (anyWaiting) {
                return "PARTIAL CONFIRMED";
            } else {
                return "WAITING";
            }
        } else {
            return "No passengers";
        }
    }

    public SeatOutboundDTO prepareOutBoundDTOObject(Booking booking, List<Passenger> passengersList) {
        SeatOutboundDTO seatOutboundDTO = new SeatOutboundDTO();

        seatOutboundDTO.setBookingId(booking.getBookingId());
        seatOutboundDTO.setBookingStatus(booking.getBookingStatus());
        seatOutboundDTO.setTrainName(booking.getTrainName());
        seatOutboundDTO.setTrainNumber(booking.getTrainNumber());
        seatOutboundDTO.setBookingDate(booking.getBookingDate());
        seatOutboundDTO.setDateOfJourney(booking.getDateOfJourney());
        seatOutboundDTO.setDayOfJourney(booking.getDayOfJourney());
        seatOutboundDTO.setSourceCity(booking.getJourneyStartingStationCity());
        seatOutboundDTO.setDestinationCity(booking.getJourneyEndingStationCity());
        seatOutboundDTO.setPnr(booking.getPnr());
        seatOutboundDTO.setMessage("BOOKING SUCCESSFUL!");

        List<PassengerOutboundDTO> passengerOutboundDTOs = new ArrayList<>();
        for (Passenger passenger : passengersList) {
            PassengerOutboundDTO passengerOutboundDTO = new PassengerOutboundDTO();
            passengerOutboundDTO.setBookingId(booking.getBookingId());
            passengerOutboundDTO.setPassengerId(passenger.getPassengerId());
            passengerOutboundDTO.setPassengerName(passenger.getPassengerName());
            passengerOutboundDTO.setPassengerAge(passenger.getPassengerAge());
            passengerOutboundDTO.setCoachNumber(passenger.getCoachNumber());
            passengerOutboundDTO.setCoachType(passenger.getCoachType());
            passengerOutboundDTO.setSeatType(passenger.getSeatType());
            passengerOutboundDTO.setSeatNumber(passenger.getSeatNumber());
            passengerOutboundDTO.setBookingStatus(passenger.getBookingStatus());
            passengerOutboundDTO.setGender(passenger.getGender());
            passengerOutboundDTO.setPnr(passenger.getPnr());
            passengerOutboundDTOs.add(passengerOutboundDTO);

        }

        seatOutboundDTO.setPassengerOutboundDTOList(passengerOutboundDTOs);
        return seatOutboundDTO;
    }

    public void deleteByBookingId(BookingDTO bookingDTO) {
        try {
            bookingRepository.deleteByBookingId(bookingDTO.getBookingId());
            passengerRepository.deleteByBookingId(bookingDTO.getBookingId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SeatBooking> getSeatAvailability(SeatDTO seatDTO) {

        List<SeatBooking> seatBookingList = seatBookingRepository.findTrainSeatByTrainNumber(
                seatDTO.getTrainNumber(), seatDTO.getDateOfJourney(), seatDTO.getCoachType());

        return seatBookingList;
    }

    @Override
    public ResponseDTO cancelBooking(SeatDTO seatDTO, String pnr) {

        try {
            List<Booking> bookingToCancelList = bookingRepository.findByBookingIdAndPnr(seatDTO.getBookingId(), pnr);
            if (bookingToCancelList == null || bookingToCancelList.isEmpty()) {
                return new ResponseDTO(false);
            }
            Booking bookingToCancel = bookingToCancelList.get(0);
            List<Booking> waitingListByTrain = bookingRepository
                    .findWaitingListByTrainNumberAndDateOfJourney(bookingToCancel.getTrainNumber(),
                            bookingToCancel.getDateOfJourney());
            List<Train> train = trainRepository.findByTrainNumber(bookingToCancel.getTrainNumber());
            List<Passenger> passengersList = passengerRepository.findByBookingId(bookingToCancel.getBookingId());
            List<Long> seatIdsToCancel = passengersList.stream().map(seat -> seat.getSeatId())
                    .collect(Collectors.toList());
            List<SeatBooking> seatsList = seatBookingRepository.findAllById(seatIdsToCancel);

            passengerRepository.deleteByBookingId(bookingToCancel.getBookingId());
            bookingToCancel.setBookingStatus("CANCELLED");
            bookingRepository.save(bookingToCancel);

            if (seatsList != null && !seatsList.isEmpty()) {
                for (SeatBooking seatBooking : seatsList) {
                    seatBooking.setIsReserved(false);
                    seatBooking.setSourceCity(null);
                    seatBooking.setSourceState(null);
                    seatBooking.setDestinationCity(null);
                    seatBooking.setDestinationState(null);
                    seatBookingRepository.save(seatBooking);
                }
            }

            if (waitingListByTrain != null && !waitingListByTrain.isEmpty()) {
                for (Booking booking : waitingListByTrain) {
                    List<Passenger> waitingPassengersList = passengerRepository.findByBookingId(booking.getBookingId());
                    waitingPassengersList.stream()
                            .filter(passenger -> passenger.getBookingStatus().equalsIgnoreCase("WAITING"))
                            .collect(Collectors.toList());
                    for (Passenger passenger : waitingPassengersList) {
                        seatAllocation(bookingToCancel, passenger, train);
                    }
                    booking.setBookingStatus(bookingStatus(waitingPassengersList));
                    bookingRepository.save(booking);
                }
            }

            return new ResponseDTO("SUCCESS", null, LocalDateTime.now(), true, "Train Booking Cancelled Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("FAILED", null, LocalDateTime.now(), false,
                    "Something Went Wrong While Cancelling Booking!");
        }

    }

}
