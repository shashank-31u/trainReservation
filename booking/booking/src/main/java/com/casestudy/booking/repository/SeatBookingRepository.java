package com.casestudy.booking.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casestudy.booking.model.SeatBooking;
import com.casestudy.booking.utils.QueryConstants;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {

    public List<SeatBooking> findByTrainId(Long trainId);

    @Query(value = QueryConstants.FETCH_TRAIN_SEAT, nativeQuery = true)
    public List<SeatBooking> findTrainSeatByTrainNumber(@Param("trainNumber") String trainNumber,
            @Param("dateOfJourney") Timestamp dateOfJourney, @Param("trainCoachType") String trainCoachType);

}