package com.casestudy.train.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "station")
@NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "station_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "train_name")
    private String trainName;

    private String source;

    private String destination;

    @Column(name = "station_name")
    private String stationName;

    private String city;

    private String state;

    @Column(name = "total_platform")
    private Long noOfPlatform;

    @Column(name = "arrival_time")
    private Time timeOfArrival;

    @Column(name = "departure_time")
    private Time timeOfDeparture;

    public Station() {
    }

    public Long getStationId() {
        return this.stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return this.stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getNoOfPlatform() {
        return this.noOfPlatform;
    }

    public void setNoOfPlatform(Long noOfPlatform) {
        this.noOfPlatform = noOfPlatform;
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

    public Time getTimeOfArrival() {
        return this.timeOfArrival;
    }

    public void setTimeOfArrival(Time timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public Time getTimeOfDeparture() {
        return this.timeOfDeparture;
    }

    public void setTimeOfDeparture(Time timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

}
