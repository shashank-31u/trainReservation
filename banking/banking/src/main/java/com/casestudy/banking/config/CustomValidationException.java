package com.casestudy.banking.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.casestudy.banking.dto.UserDTO;

public class CustomValidationException extends ConstraintViolationException {

    private final UserDTO userDTO;

    public CustomValidationException(Set<ConstraintViolation<UserDTO>> constraintViolations, UserDTO userDTODetails) {
        super(constraintViolations);
        this.userDTO = userDTODetails;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

}
