package com.casestudy.train.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CoachDTO {

    private Long coachId;

    @NotNull
    @NotBlank
    private String coachNumber;

    @NotNull
    @NotBlank
    private String coachType;

    @NotNull
    private Long coachNormalFare;
    private Long coachTaktalFare;
    private Long coachPremiumTaktalFare;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<SeatDTO> seats;

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

    public List<SeatDTO> getSeats() {
        return this.seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public Long getCoachId() {
        return this.coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

}
