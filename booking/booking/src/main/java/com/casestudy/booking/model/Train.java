package com.casestudy.booking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "trains")
@NamedQuery(name = "Train.findAll", query = "SELECT t FROM Train t")
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "train_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_type")
    private String trainType;

    private String source;

    private String destination;

    @Column(name = "total_distance")
    private String totalDistance;

    @ElementCollection
    @Column(name = "scheduled_days")
    private List<String> scheduledDay;

    @Transient
    private List<Coach> coachList;

    @Transient
    private List<Station> stationList;

    public Train() {
    }

    public Long getTrainId() {
        return this.trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
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

    public String getTrainType() {
        return this.trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<String> getScheduledDay() {
        return this.scheduledDay;
    }

    public void setScheduledDay(List<String> scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public List<Coach> getCoachList() {
        return this.coachList;
    }

    public void setCoachList(List<Coach> coachList) {
        this.coachList = coachList;
    }

    public List<Station> getStationList() {
        return this.stationList;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

}
