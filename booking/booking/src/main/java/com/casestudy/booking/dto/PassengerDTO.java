package com.casestudy.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PassengerDTO {

    private Long passengerId;

    @NotNull
    @NotBlank
    private String passengerName;

    @NotNull
    private Integer passengerAge;

    @NotNull
    @NotBlank
    private String gender;

    public PassengerDTO() {
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerAge() {
        return this.passengerAge;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
