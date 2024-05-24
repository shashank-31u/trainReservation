package com.casestudy.train.dto;

import java.sql.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StationDTO {

    private Long stationId;

    @NotNull
    @NotBlank
    private String stationName;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String state;
    private Long noOfPlatform;
    private Boolean isSource;
    private Boolean isDestination;
    private Time timeOfArrival;
    private Time timeOfDeparture;

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

    public Boolean isIsSource() {
        return this.isSource;
    }

    public Boolean getIsSource() {
        return this.isSource;
    }

    public void setIsSource(Boolean isSource) {
        this.isSource = isSource;
    }

    public Boolean isIsDestination() {
        return this.isDestination;
    }

    public Boolean getIsDestination() {
        return this.isDestination;
    }

    public void setIsDestination(Boolean isDestination) {
        this.isDestination = isDestination;
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

    public Long getStationId() {
        return this.stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "{" +
                " stationName='" + getStationName() + "'" +
                ", city='" + getCity() + "'" +
                ", state='" + getState() + "'" +
                ", noOfPlatform='" + getNoOfPlatform() + "'" +
                ", isSource='" + isIsSource() + "'" +
                ", isDestination='" + isIsDestination() + "'" +
                "}";
    }

}
