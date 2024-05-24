package com.casestudy.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.TrainSchedule;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {

}
