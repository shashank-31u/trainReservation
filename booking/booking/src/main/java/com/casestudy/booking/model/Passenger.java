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
@Table(name = "train_passengers")
@NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p")
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "passenger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "booking_id")
    private Long bookingId;

    private String pnr;

    @Column(name = "train_name")
    private String trainName;

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

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "passenger_age")
    private Integer passengerAge;

    @Column(name = "gender")
    private String gender;

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

    @Column(name = "booking_status")
    private String bookingStatus;

    public Passenger() {
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
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

    public Long getSeatId() {
        return this.seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerAge() {
        return this.passengerAge;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
