package com.casestudy.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.TrainCounter;

@Repository
public interface TrainCounterRepository extends JpaRepository<TrainCounter, Long> {

}
