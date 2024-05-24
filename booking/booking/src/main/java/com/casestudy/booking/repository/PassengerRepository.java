package com.casestudy.booking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    public List<Passenger> findByBookingId(Long bookingId);

    @Transactional
    @Modifying
    public void deleteByBookingId(Long bookingId);

}
