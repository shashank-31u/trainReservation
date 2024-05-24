package com.casestudy.train.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = "select * from train_seat where train_id =:trainId and coach_id =:coachId", nativeQuery = true)
    public List<Seat> findSeatsByTrainIdAndCoachId(@Param("trainId") Long trainId, @Param("coachId") Long coachId);
}
