package com.casestudy.train.dto;

import java.sql.Timestamp;

public class TrainSearchDTO {

    private Long trainSearchId;
    private String sourceCity;
    private String destinationCity;
    private Timestamp dateOfJourney;

    public Long getTrainSearchId() {
        return this.trainSearchId;
    }

    public void setTrainSearchId(Long trainSearchId) {
        this.trainSearchId = trainSearchId;
    }

    public String getSourceCity() {
        return this.sourceCity;
    }

    public void setSourceCity(String stationFrom) {
        this.sourceCity = stationFrom;
    }

    public String getDestinationCity() {
        return this.destinationCity;
    }

    public void setDestinationCity(String stationTo) {
        this.destinationCity = stationTo;
    }

    public Timestamp getDateOfJourney() {
        return this.dateOfJourney;
    }

    public void setDateOfJourney(Timestamp dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

}
