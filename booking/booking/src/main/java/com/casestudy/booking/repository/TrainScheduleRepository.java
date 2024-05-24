package com.casestudy.booking.repository;

import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.booking.model.TrainSchedule;
import com.casestudy.booking.utils.QueryConstants;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {

    @Query(value = QueryConstants.FETCH_TRAIN_SCHEDULE, nativeQuery = true)
//    public List<TrainSchedule> findScheduleByTrainNumber(@Param("trainNumber") String trainNumber,
//            @Param("sourceCity") String sourceCity,
//            @Param("destinationCity") String destinationCity,
//            @Param("dateOfJourney") String dateFormated);
    public List<TrainSchedule> findScheduleByTrainNumber(@Param("trainNumber") String trainNumber,
            @Param("sourceCity") String sourceCity,
            @Param("destinationCity") String destinationCity,
            @Param("dateOfJourney") String dateFormated);

}
