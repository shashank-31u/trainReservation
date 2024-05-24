package com.casestudy.train.interfaces;

import java.util.List;

import com.casestudy.train.dto.ResponseDTO;
import com.casestudy.train.dto.TrainInboundDTO;
import com.casestudy.train.dto.TrainOutboundDTO;
import com.casestudy.train.model.Train;

public interface TrainService {

    public TrainOutboundDTO addTrainInfo(TrainInboundDTO trainInboundDTODetails);

    public Train getTrainDetailsByTrainNumber(String trainNumber);

    public List<Train> getAllTrainDetails();

    public TrainOutboundDTO updateTrainInfo(TrainInboundDTO trainInboundDTODetails);

    public ResponseDTO deleteTrainInfo(TrainInboundDTO trainInboundDTODetails);

}
