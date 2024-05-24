package com.casestudy.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query(value = "select * from train_coach where train_id =:trainId", nativeQuery = true)
    public List<Coach> findCoachByTrainId(@Param("trainId") Long trainId);

}
