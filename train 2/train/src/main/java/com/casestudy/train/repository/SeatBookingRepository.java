package com.casestudy.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.SeatBooking;

import java.util.List;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {

    public List<SeatBooking> findByTrainNumber(String trainNumber);

}