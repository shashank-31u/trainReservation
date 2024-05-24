package com.casestudy.train.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TrainInboundDTO {

    private Long trainId;

    @NotNull
    @NotBlank
    private String trainName;

    private String trainNumber;

    @NotNull
    @NotBlank
    private String trainType;

    @NotNull
    @NotBlank
    private String totalDistance;

    @NotNull
    @Size(min = 1)
    private List<String> scheduledDays;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<CoachDTO> coachDTO;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<StationDTO> stationDTO;

    private String sourceCity;

    private String destinationCity;

    public String getTrainName() {
        return this.trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
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

    public List<CoachDTO> getCoachDTO() {
        return this.coachDTO;
    }

    public void setCoachDTO(List<CoachDTO> coachDTO) {
        this.coachDTO = coachDTO;
    }

    public List<StationDTO> getStationDTO() {
        return this.stationDTO;
    }

    public void setStationDTO(List<StationDTO> stationDTO) {
        this.stationDTO = stationDTO;
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

    @Override
    public String toString() {
        return "{" +
                " trainId='" + getTrainId() + "'" +
                ", trainName='" + getTrainName() + "'" +
                ", trainNumber='" + getTrainNumber() + "'" +
                ", trainType='" + getTrainType() + "'" +
                ", totalDistance='" + getTotalDistance() + "'" +
                "}";
    }

    public String getSourceCity() {
        return this.sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return this.destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

}
