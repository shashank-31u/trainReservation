package com.casestudy.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.train.model.TrainPrice;

@Repository
public interface TrainPriceRepository extends JpaRepository<TrainPrice, Long> {

}
