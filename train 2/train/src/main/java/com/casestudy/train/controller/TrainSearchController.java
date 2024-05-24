package com.casestudy.train.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.train.dto.ResponseDTO;
import com.casestudy.train.dto.TrainSearchDTO;
import com.casestudy.train.model.TrainFare;
import com.casestudy.train.model.TrainSchedule;
import com.casestudy.train.services.TrainSearchImpl;
@RestController
@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/train")
public class TrainSearchController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    @Autowired
    private TrainSearchImpl trainSearchImpl;

    @RequestMapping(value = "/searchTrain", method = RequestMethod.POST)
    public ResponseEntity<?> getTrainByTrainNumber(@RequestBody TrainSearchDTO trainSearchDTO) {

        try {
            if (trainSearchDTO != null) {
                List<TrainSchedule> trainSchedules = trainSearchImpl.getTrainSchedule(trainSearchDTO);
                if (trainSchedules != null && !trainSchedules.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(trainSchedules);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Train Founds Between Source: [" + trainSearchDTO.getSourceCity()
                                    + "] and Destination: [" + trainSearchDTO.getDestinationCity() + "]"));
                }
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/getFareByTrainNumber", method = RequestMethod.POST)
    public ResponseEntity<?> getFareByTrainNumber(@RequestBody TrainSearchDTO trainSearchDTO,
            @RequestParam("trainNumber") String trainNumber) {

        try {
            if (trainSearchDTO != null) {
                List<TrainFare> trainFareList = trainSearchImpl.getTrainFareByTrainNumber(trainSearchDTO,
                        trainNumber);
                if (trainFareList != null && !trainFareList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(trainFareList);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Train Fare List Found For Train Number: [" + trainNumber
                                    + "]"));
                }
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Fetching The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

}