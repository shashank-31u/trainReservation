package com.casestudy.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.TrainCounter;
import com.casestudy.train.utils.QueriesConstants;

@Repository
public interface TrainCounterRepository extends JpaRepository<TrainCounter, Long> {

    @Query(value = QueriesConstants.FETCH_COUNTER_VALUE, nativeQuery = true)
    public TrainCounter findTrainCounterValue();

}
