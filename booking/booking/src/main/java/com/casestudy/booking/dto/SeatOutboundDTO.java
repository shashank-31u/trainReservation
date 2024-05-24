package com.casestudy.booking.dto;

import java.sql.Timestamp;

import java.util.List;

public class SeatOutboundDTO {

    private Long bookingId;
    private String pnr;
    private Timestamp bookingDate;
    private String bookingStatus;
    private String sourceCity;
    private String destinationCity;
    private String trainName;
    private String trainNumber;
    private Timestamp dateOfJourney;
    private String dayOfJourney;
    private String message;
    private List<PassengerOutboundDTO> passengerOutboundDTOList;

    public SeatOutboundDTO() {
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
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

    public String getTrainName() {
        return this.trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Timestamp getDateOfJourney() {
        return this.dateOfJourney;
    }

    public void setDateOfJourney(Timestamp dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public String getDayOfJourney() {
        return this.dayOfJourney;
    }

    public void setDayOfJourney(String dayOfJourney) {
        this.dayOfJourney = dayOfJourney;
    }

    public List<PassengerOutboundDTO> getPassengerOutboundDTOList() {
        return this.passengerOutboundDTOList;
    }

    public void setPassengerOutboundDTOList(List<PassengerOutboundDTO> passengerOutboundDTOList) {
        this.passengerOutboundDTOList = passengerOutboundDTOList;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
