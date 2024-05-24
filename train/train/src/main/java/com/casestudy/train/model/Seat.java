package com.casestudy.train.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "train_seat")
@NamedQuery(name = "Seat.findAll", query = "SELECT s FROM Seat s")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Column(name = "train_seat_number")
    private String seatNumber;

    @Column(name = "train_seat_type")
    private String seatType;

    @Column(name = "train_reserved")
    private Boolean isReserved;

    public Seat() {
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

}
