package com.casestudy.booking.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookingDTO {

    private Long bookingId;

    @NotNull
    @NotBlank
    private String trainNumber;

    @NotNull
    @NotBlank
    private String coachType;

    @NotNull
    @NotBlank
    private String sourceCity;

    @NotNull
    @NotBlank
    private String destinationCity;

    @NotNull
    private Timestamp dateOfJourney;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<PassengerDTO> passengerDTOList;

    @NotNull
    @NotBlank
    String bookingType;

    public BookingDTO() {
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getCoachType() {
        return this.coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public String getSourceCity() {
        return this.sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return this.destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Timestamp getDateOfJourney() {
        return this.dateOfJourney;
    }

    public void setDateOfJourney(Timestamp dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public List<PassengerDTO> getPassengerDTOList() {
        return this.passengerDTOList;
    }

    public void setPassengerDTOList(List<PassengerDTO> passengerDTOList) {
        this.passengerDTOList = passengerDTOList;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

}
