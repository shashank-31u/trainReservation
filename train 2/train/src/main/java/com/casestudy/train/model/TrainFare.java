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
@Table(name = "train_fare")
@NamedQuery(name = "TrainFare.findAll", query = "SELECT tf FROM TrainFare tf")
public class TrainFare implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "train_fair_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainFairId;

    @Column(name = "train_schedule_id")
    private Long trainScheduleId;

    @Column(name = "source_station_id")
    private Long sourceStationId;

    @Column(name = "destination_station_id")
    private Long destinationStationId;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "source_station_name")
    private String sourceStationName;

    @Column(name = "destination_station_name")
    private String destinationStationName;

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

    @Column(name = "distance_between_source_destination")
    private Integer distanceBetweenSourceDestination;

    public TrainFare() {
    }

    public Long getTrainFairId() {
        return this.trainFairId;
    }

    public void setTrainFairId(Long trainFairId) {
        this.trainFairId = trainFairId;
    }

    public Long getTrainScheduleId() {
        return this.trainScheduleId;
    }

    public void setTrainScheduleId(Long trainScheduleId) {
        this.trainScheduleId = trainScheduleId;
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

    public String getTrainName() {
        return this.trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSourceStationName() {
        return this.sourceStationName;
    }

    public void setSourceStationName(String sourceStationName) {
        this.sourceStationName = sourceStationName;
    }

    public String getDestinationStationName() {
        return this.destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
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

    public Integer getDistanceBetweenSourceDestination() {
        return this.distanceBetweenSourceDestination;
    }

    public void setDistanceBetweenSourceDestination(Integer distanceBetweenSourceDestination) {
        this.distanceBetweenSourceDestination = distanceBetweenSourceDestination;
    }

}
