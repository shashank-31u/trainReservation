package com.casestudy.train.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.Train;
import com.casestudy.train.utils.QueriesConstants;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query(value = QueriesConstants.FETCH_TRAINS_BY_TRAIN_NAME, nativeQuery = true)
    public List<Train> findByTrainName(@Param("trainName") String trainName);

    public Optional<Train> findById(Long trainId);

    public List<Train> findByTrainNumber(String trainNumber);

}
