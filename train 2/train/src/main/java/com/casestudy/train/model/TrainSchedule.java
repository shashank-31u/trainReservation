package com.casestudy.train.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "train_schedule")
@NamedQuery(name = "TrainSchedule.findAll", query = "SELECT ts FROM TrainSchedule ts")
public class TrainSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "train_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "train_source")
    private String trainSource;

    @Column(name = "train_destination")
    private String trainDestination;

    @Column(name = "source_platform_number")
    private Integer sourcePlatfromNumber;

    @Column(name = "destination_platform_number")
    private Integer destinationPlatfromNumber;

    @Column(name = "source_arrival_time")
    private Time sourceTimeOfArrival;

    @Column(name = "source_departure_time")
    private Time sourceTimeOfDeparture;

    @Column(name = "destination_arrival_time")
    private Time destinationTimeOfArrival;

    @Column(name = "destination_departure_time")
    private Time destinationTimeOfDeparture;

    @Column(name = "seats_available")
    private Long seatsAvailable;

    @Column(name = "is_daily")
    private Boolean isDaily;

    @Column(name = "source_halt")
    private Long sourceHalt;

    @Column(name = "destination_halt")
    private Long destinationHalt;

    @Column(name = "date_of_journey")
    private Timestamp dateOfJourney;

    @Column(name = "day_of_journey")
    private String dayOfJourney;

    @Column(name = "source_city")
    private String sourceCity;

    @Column(name = "source_state")
    private String sourceState;

    @Column(name = "destination_city")
    private String destinationCity;

    @Column(name = "destination_state")
    private String destinationState;

    @Column(name = "distance_between_source_destination")
    private Integer distanceBetweenSourceDestination;

    public TrainSchedule() {
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

    public void setSourceStationId(Long stationFromId) {
        this.sourceStationId = stationFromId;
    }

    public Long getDestinationStationId() {
        return this.destinationStationId;
    }

    public void setDestinationStationId(Long stationToId) {
        this.destinationStationId = stationToId;
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

    public void setSourceStationName(String stationFrom) {
        this.sourceStationName = stationFrom;
    }

    public String getDestinationStationName() {
        return this.destinationStationName;
    }

    public void setDestinationStationName(String stationTo) {
        this.destinationStationName = stationTo;
    }

    public String getTrainSource() {
        return this.trainSource;
    }

    public void setTrainSource(String trainSource) {
        this.trainSource = trainSource;
    }

    public String getTrainDestination() {
        return this.trainDestination;
    }

    public void setTrainDestination(String trainDestination) {
        this.trainDestination = trainDestination;
    }

    public Integer getSourcePlatfromNumber() {
        return this.sourcePlatfromNumber;
    }

    public void setSourcePlatfromNumber(Integer sourcePlatfromNumber) {
        this.sourcePlatfromNumber = sourcePlatfromNumber;
    }

    public Integer getDestinationPlatfromNumber() {
        return this.destinationPlatfromNumber;
    }

    public void setDestinationPlatfromNumber(Integer destinationPlatfromNumber) {
        this.destinationPlatfromNumber = destinationPlatfromNumber;
    }

    public Time getSourceTimeOfArrival() {
        return this.sourceTimeOfArrival;
    }

    public void setSourceTimeOfArrival(Time timeOfArrivalAtSource) {
        this.sourceTimeOfArrival = timeOfArrivalAtSource;
    }

    public Time getSourceTimeOfDeparture() {
        return this.sourceTimeOfDeparture;
    }

    public void setSourceTimeOfDeparture(Time timeOfDepartureAtSource) {
        this.sourceTimeOfDeparture = timeOfDepartureAtSource;
    }

    public Time getDestinationTimeOfArrival() {
        return this.destinationTimeOfArrival;
    }

    public void setDestinationTimeOfArrival(Time timeOfArrivalAtDestination) {
        this.destinationTimeOfArrival = timeOfArrivalAtDestination;
    }

    public Time getDestinationTimeOfDeparture() {
        return this.destinationTimeOfDeparture;
    }

    public void setDestinationTimeOfDeparture(Time timeOfDepartureAtDestination) {
        this.destinationTimeOfDeparture = timeOfDepartureAtDestination;
    }

    public Long getSeatsAvailable() {
        return this.seatsAvailable;
    }

    public void setSeatsAvailable(Long seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public Boolean isIsDaily() {
        return this.isDaily;
    }

    public Boolean getIsDaily() {
        return this.isDaily;
    }

    public void setIsDaily(Boolean isDaily) {
        this.isDaily = isDaily;
    }

    public Long getSourceHalt() {
        return this.sourceHalt;
    }

    public void setSourceHalt(Long sourceHalt) {
        this.sourceHalt = sourceHalt;
    }

    public Long getDestinationHalt() {
        return this.destinationHalt;
    }

    public void setDestinationHalt(Long destinationHalt) {
        this.destinationHalt = destinationHalt;
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

    public String getSourceCity() {
        return this.sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getSourceState() {
        return this.sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public String getDestinationCity() {
        return this.destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationState() {
        return this.destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public Integer getDistanceBetweenSourceDestination() {
        return this.distanceBetweenSourceDestination;
    }

    public void setDistanceBetweenSourceDestination(Integer distanceBetweenSourceDestination) {
        this.distanceBetweenSourceDestination = distanceBetweenSourceDestination;
    }

}
