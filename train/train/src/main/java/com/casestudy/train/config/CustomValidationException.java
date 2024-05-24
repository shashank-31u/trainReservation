package com.casestudy.train.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.casestudy.train.dto.TrainInboundDTO;

public class CustomValidationException extends ConstraintViolationException {

    public CustomValidationException(Set<ConstraintViolation<TrainInboundDTO>> constraintViolations) {
        super(constraintViolations);
    }

}
