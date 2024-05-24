package com.casestudy.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TrainDetails")
public class TrainModel {
	@Id
    @Column(name = "TrainName")
	private String trainName;
	
	@Column(name = "TrainNo")
	private String trainNo;
	
	@Column(name = "StartsFrom")
	private String startsFrom;
	
	@Column(name = "DepartureTime")
	private String departureTime;
	
	@Column(name = "Destination")
	private String destination;
	
	@Column(name = "ArrivalTime")
	private String arrivalTime;
	
	@Column(name = "TrainType")
	private String trainType;
	
	@Column(name = "Rundays")
	private List<String> rundays;
	
	@Column(name = "Fare")
	private int fare;
	
	
	
	

}
