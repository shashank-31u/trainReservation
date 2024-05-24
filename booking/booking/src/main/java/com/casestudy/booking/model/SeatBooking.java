package com.casestudy.booking.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "seat_booking")
@NamedQuery(name = "SeatBooking.findAll", query = "SELECT b FROM SeatBooking b")
public class SeatBooking implements Serializable {

    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_coach_number")
    private String coachNumber;

    @Column(name = "train_coach_type")
    private String coachType;

    @Column(name = "train_seat_number")
    private String seatNumber;

    @Column(name = "train_seat_type")
    private String seatType;

    @Column(name = "train_reserved")
    private Boolean isReserved;

    @Column(name = "date_of_journey")
    private Timestamp dateOfJourney;

    @Column(name = "day_of_journey")
    private String dayOfJourney;

    @Column(name = "source_city")
    private String sourceCity;

    @Column(name = "source_state")
    private String sourceState;

    @Column(name = "destination_city")
    private String destinationCity;

    @Column(name = "destination_state")
    private String destinationState;

    public SeatBooking() {
    }

    public Long getSeatId() {
        return this.seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getTrainId() {
        return this.trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getCoachId() {
        return this.coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getCoachNumber() {
        return this.coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
    }

    public String getCoachType() {
        return this.coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return this.seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Boolean isIsReserved() {
        return this.isReserved;
    }

    public Boolean getIsReserved() {
        return this.isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
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

    public String getSourceCity() {
        return this.sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getSourceState() {
        return this.sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public String getDestinationCity() {
        return this.destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationState() {
        return this.destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

}
