package com.casestudy.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "select * from station where train_id =:trainId", nativeQuery = true)
    public List<Station> findStationsByTrainId(@Param("trainId") Long trainId);

    public List<Station> findStationsByCity(@Param("city") String city);

}
