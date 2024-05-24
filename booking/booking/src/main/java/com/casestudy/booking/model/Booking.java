package com.casestudy.booking.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "train_booking")
@NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "train_id")
    private Long trainId;

    private String pnr;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_seat_number")
    private String seatNumber;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "booking_type")
    private String bookingType;

    @Column(name = "booking_date")
    private Timestamp bookingDate;

    @Column(name = "source_station_id")
    private Long sourceStationId;

    @Column(name = "destination_station_id")
    private Long destinationStationId;

    @Column(name = "date_of_journey")
    private Timestamp dateOfJourney;

    @Column(name = "day_of_journey")
    private String dayOfJourney;

    @Column(name = "journey_starting_station_name")
    private String journeyStartingStationName;

    @Column(name = "journey_starting_station_city")
    private String journeyStartingStationCity;

    @Column(name = "journey_ending_station_name")
    private String journeyEndingStationName;

    @Column(name = "journey_ending_station_city")
    private String journeyEndingStationCity;

    @Column(name = "source_state")
    private String sourceState;

    @Column(name = "destination_state")
    private String destinationState;

    @Transient
    private List<Passenger> passengersList;

    public Booking() {
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getTrainId() {
        return this.trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
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

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Timestamp getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(Timestamp timestamp) {
        this.bookingDate = timestamp;
    }

    public Long getSourceStationId() {
        return this.sourceStationId;
    }

    public void setSourceStationId(Long sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public Long getDestinationStationId() {
        return this.destinationStationId;
    }

    public void setDestinationStationId(Long destinationStationId) {
        this.destinationStationId = destinationStationId;
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

    public String getSourceState() {
        return this.sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public String getDestinationState() {
        return this.destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public List<Passenger> getPassengersList() {
        return this.passengersList;
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public String getJourneyStartingStationName() {
        return this.journeyStartingStationName;
    }

    public void setJourneyStartingStationName(String journeyStartingStationName) {
        this.journeyStartingStationName = journeyStartingStationName;
    }

    public String getJourneyStartingStationCity() {
        return this.journeyStartingStationCity;
    }

    public void setJourneyStartingStationCity(String journeyStartingStationCity) {
        this.journeyStartingStationCity = journeyStartingStationCity;
    }

    public String getJourneyEndingStationName() {
        return this.journeyEndingStationName;
    }

    public void setJourneyEndingStationName(String journeyEndingStationName) {
        this.journeyEndingStationName = journeyEndingStationName;
    }

    public String getJourneyEndingStationCity() {
        return this.journeyEndingStationCity;
    }

    public void setJourneyEndingStationCity(String journeyEndingStationCity) {
        this.journeyEndingStationCity = journeyEndingStationCity;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

}
