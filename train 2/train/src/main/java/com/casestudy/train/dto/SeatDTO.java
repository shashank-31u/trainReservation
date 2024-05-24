package com.casestudy.train.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SeatDTO {

    private Long seatId;

    @NotNull
    private String seatNumber;

    @NotNull
    private String seatType;

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return this.seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Long getSeatId() {
        return this.seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "{" +
                " seatNumber='" + getSeatNumber() + "'" +
                ", seatType='" + getSeatType() + "'" +
                "}";
    }

}
