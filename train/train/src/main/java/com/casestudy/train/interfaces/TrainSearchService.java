package com.casestudy.train.interfaces;

import java.util.List;

import com.casestudy.train.dto.TrainSearchDTO;
import com.casestudy.train.model.TrainSchedule;

public interface TrainSearchService {

    public List<TrainSchedule> getTrainSchedule(TrainSearchDTO trainSearchDTO);

}