package com.casestudy.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.TrainFare;

@Repository
public interface TrainFareRepository extends JpaRepository<TrainFare, Long> {

    public List<TrainFare> findByTrainScheduleIdAndTrainNumber(Long trainScheduleId, String trainNumber);
}