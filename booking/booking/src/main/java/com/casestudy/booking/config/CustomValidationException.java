package com.casestudy.booking.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.casestudy.booking.dto.BookingDTO;

public class CustomValidationException extends ConstraintViolationException {

    public CustomValidationException(Set<ConstraintViolation<BookingDTO>> constraintViolations) {
        super(constraintViolations);
    }

}
