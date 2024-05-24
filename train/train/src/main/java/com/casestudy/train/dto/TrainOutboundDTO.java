package com.casestudy.train.dto;

import java.util.List;

public class TrainOutboundDTO {

    private Long trainId;
    private String trainNumber;
    private String source;
    private String destination;
    private String trainType;
    private String totalDistance;
    private List<String> scheduledDays;

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

    public String getTrainType() {
        return this.trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<String> getScheduledDays() {
        return this.scheduledDays;
    }

    public void setScheduledDays(List<String> scheduledDays) {
        this.scheduledDays = scheduledDays;
    }

}
