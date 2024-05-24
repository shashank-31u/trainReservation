package com.casestudy.booking.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.Booking;
import com.casestudy.booking.utils.QueryConstants;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    public Booking findByBookingId(Long bookingId);

    @Transactional
    @Modifying
    public void deleteByBookingId(Long bookingId);

    @Query(value = QueryConstants.FETCH_BOOKING_BY_PNR, nativeQuery = true)
    public List<Booking> findByBookingIdAndPnr(Long bookingId, String pnr);

    @Query(value = QueryConstants.FETCH_WAITING_LIST_BY_TRAIN, nativeQuery = true)
    public List<Booking> findWaitingListByTrainNumberAndDateOfJourney(String trainNumber, Timestamp dateOfJourney);
}
