package com.casestudy.booking.dto;

public class BookingOutboundDTO {

    Long bookingId;
    String bookingStatus;
    String message;
    Long fare;
    String bookingType;

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getFare() {
        return this.fare;
    }

    public void setFare(Long fare) {
        this.fare = fare;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

}
