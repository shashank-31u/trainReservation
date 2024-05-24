package com.casestudy.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    public Optional<Train> findById(Long trainId);

    public List<Train> findByTrainNumber(String trainNumber);

}
