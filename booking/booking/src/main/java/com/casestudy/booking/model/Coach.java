package com.casestudy.booking.model;

import java.io.Serializable;
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
@Table(name = "train_coach")
@NamedQuery(name = "Coach.findAll", query = "SELECT c FROM Coach c")
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "coach_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_coach_number")
    private String coachNumber;

    @Column(name = "train_coach_type")
    private String coachType;

    @Column(name = "train_coach_normal_fare")
    private Long coachNormalFare;

    @Column(name = "train_coach_tatkal_fare")
    private Long coachTaktalFare;

    @Column(name = "train_coach_premium_tatkal_fare")
    private Long coachPremiumTaktalFare;

    @Transient
    private List<Seat> seat;

    public Coach() {
    }

    public Long getCoachId() {
        return this.coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getTrainId() {
        return this.trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
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

    public Long getCoachNormalFare() {
        return this.coachNormalFare;
    }

    public void setCoachNormalFare(Long coachNormalFare) {
        this.coachNormalFare = coachNormalFare;
    }

    public Long getCoachTaktalFare() {
        return this.coachTaktalFare;
    }

    public void setCoachTaktalFare(Long coachTaktalFare) {
        this.coachTaktalFare = coachTaktalFare;
    }

    public Long getCoachPremiumTaktalFare() {
        return this.coachPremiumTaktalFare;
    }

    public void setCoachPremiumTaktalFare(Long coachPremiumTaktalFare) {
        this.coachPremiumTaktalFare = coachPremiumTaktalFare;
    }

    public List<Seat> getSeat() {
        return this.seat;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }

}
