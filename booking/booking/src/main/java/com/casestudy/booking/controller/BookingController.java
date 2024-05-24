package com.casestudy.booking.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.booking.config.CustomValidationException;
import com.casestudy.booking.dto.BookingDTO;
import com.casestudy.booking.dto.BookingOutboundDTO;
import com.casestudy.booking.dto.ResponseDTO;
import com.casestudy.booking.dto.SeatDTO;
import com.casestudy.booking.dto.SeatOutboundDTO;
import com.casestudy.booking.model.SeatBooking;
import com.casestudy.booking.services.BookingServiceImpl;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/booking")
public class BookingController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @RequestMapping(value = "/createBooking", method = RequestMethod.POST)
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {

        try {
            if (bookingDTO != null) {

                Set<ConstraintViolation<BookingDTO>> violations = validator.validate(bookingDTO);
                if (!violations.isEmpty()) {
                    throw new CustomValidationException(violations);
                }

                BookingOutboundDTO bookingOutboundDTO = bookingServiceImpl.createBooking(bookingDTO);
                if (bookingOutboundDTO != null &&
                        bookingOutboundDTO.getBookingId() != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(bookingOutboundDTO);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Train Founds Between Source: [" + bookingDTO.getSourceCity()
                                    + "] and Destination: [" + bookingDTO.getDestinationCity() + "]"));
                }
            }
            return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                    "Request Not Sent Properly!"));
        } catch (CustomValidationException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public ResponseEntity<?> saveBooking(@RequestBody BookingDTO bookingDTO,
            @RequestParam("isPaymentDone") String isPaymentDone) {

        try {
            if (bookingDTO != null) {
                if (isPaymentDone != null && !isPaymentDone.equalsIgnoreCase("true")) {
                    bookingServiceImpl.deleteByBookingId(bookingDTO);
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "Payment Failed"));
                }

                SeatOutboundDTO seatOutboundDTO = bookingServiceImpl.saveBooking(bookingDTO);
                if (seatOutboundDTO != null &&
                        seatOutboundDTO.getBookingId() != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(seatOutboundDTO);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "Booking Failed!"));
                }
            }
            return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                    "Request Not Sent Properly!"));
        } catch (CustomValidationException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/seatAvailability", method = RequestMethod.POST)
    public ResponseEntity<?> seatAvailability(@RequestBody SeatDTO seatDTO) {

        try {
            if (seatDTO != null) {

                List<SeatBooking> seatBookingList = bookingServiceImpl.getSeatAvailability(seatDTO);
                if (seatBookingList != null &&
                        !seatBookingList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(seatBookingList);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Seats Available Between Source: [" + seatDTO.getSourceCity()
                                    + "] and Destination: [" + seatDTO.getDestinationCity() + "]"));
                }
            }
            return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                    "Request Not Sent Properly!"));
        } catch (CustomValidationException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
    public ResponseEntity<?> cancelBooking(@RequestBody SeatDTO seatDTO, @RequestParam("pnr") String pnr) {

        try {
            ResponseDTO responseDTO = bookingServiceImpl.cancelBooking(seatDTO, pnr);
            if (responseDTO.getSuccess()) {
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            } else {
                return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                        "No Booking Found With PNR: [" + pnr + "]"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

}
