package com.casestudy.train.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.train.config.CustomValidationException;
import com.casestudy.train.dto.ResponseDTO;
import com.casestudy.train.dto.TrainInboundDTO;
import com.casestudy.train.dto.TrainOutboundDTO;
import com.casestudy.train.model.Train;
import com.casestudy.train.services.TrainServiceImpl;
import com.casestudy.train.utils.StringUtils;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/train")
public class TrainController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private TrainServiceImpl trainServiceImpl;

    @RequestMapping(value = "/addTrainInfo", method = RequestMethod.POST)
    public ResponseEntity<?> saveTrainDetails(@RequestBody TrainInboundDTO trainDetails) {

        try {
            if (null != trainDetails) {
                Set<ConstraintViolation<TrainInboundDTO>> violations = validator.validate(trainDetails);
                if (!violations.isEmpty()) {
                    throw new CustomValidationException(violations);
                }

                TrainOutboundDTO trainOutboundDTO = trainServiceImpl.addTrainInfo(trainDetails);
                if (trainOutboundDTO == null) {
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setMessage(FAILED);
                    responseDTO.setSuccess(false);
                    responseDTO.setResponseTime(LocalDateTime.now());
                    responseDTO.setErrorMessage(
                            Arrays.asList("Duplication Record Entry!, Train already exists with Train Name: "
                                    + trainDetails.getTrainName()));
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(responseDTO);
                } else if (trainOutboundDTO == null || trainOutboundDTO.getTrainId() == null
                        || trainOutboundDTO.getTrainId() == 0L) {
                    throw new Exception();
                }

                return ResponseEntity.ok().body(trainOutboundDTO);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(FAILED, Arrays.asList("Request not sent properly!"), LocalDateTime.now(),
                            false, null));

        } catch (CustomValidationException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Saving Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/getTrainDetails", method = RequestMethod.GET)
    public ResponseEntity<?> getTrainByTrainNumber(@RequestParam("trainNumber") String trainNumber) {

        try {
            if (!StringUtils.checkNullString(trainNumber).isEmpty()) {
                Train train = trainServiceImpl.getTrainDetailsByTrainNumber(trainNumber);
                if (train != null && train.getTrainId() != null && train.getTrainId() != 0L) {
                    return ResponseEntity.status(HttpStatus.OK).body(train);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Train Found With That Train Number: " + trainNumber));
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
                    Arrays.asList("Something Went Wrong While Saving Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/getAllTrainDetails", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTrains() {
        try {
            List<Train> trains = trainServiceImpl.getAllTrainDetails();
            if (trains != null && !trains.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(trains);
            } else {
                return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                        "No Trains Found In The Database!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Saving Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/updateTrainInfo", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTrainInfo(@RequestBody TrainInboundDTO trainDetails) {

        try {
            if (null != trainDetails) {
                Set<ConstraintViolation<TrainInboundDTO>> violations = validator.validate(trainDetails);
                if (!violations.isEmpty()) {
                    throw new CustomValidationException(violations);
                }

                TrainOutboundDTO trainOutboundDTO = trainServiceImpl.updateTrainInfo(trainDetails);
                if (trainOutboundDTO != null && trainOutboundDTO.getTrainId() != null
                        && trainOutboundDTO.getTrainId() != 0L) {
                    return ResponseEntity.ok().body(trainOutboundDTO);
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(SUCCESS, null, LocalDateTime.now(), true,
                            "No Train Found With That Train Number: " + trainDetails.getTrainNumber()));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(FAILED, Arrays.asList("Request not sent properly!"), LocalDateTime.now(),
                            false, null));

        } catch (CustomValidationException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(FAILED);
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(
                    Arrays.asList("Something Went Wrong While Updating The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

    @RequestMapping(value = "/deleteTrainInfo", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTrainInfo(@RequestBody TrainInboundDTO trainDetails) {

        try {
            if (null != trainDetails) {
                ResponseDTO responseDTO = trainServiceImpl.deleteTrainInfo(trainDetails);
                if (responseDTO.getSuccess()) {
                    return ResponseEntity.ok().body(responseDTO);
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
                    Arrays.asList("Something Went Wrong While Updating The Record!"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }

    }

}
