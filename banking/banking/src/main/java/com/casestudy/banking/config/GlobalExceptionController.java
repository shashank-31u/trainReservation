package com.casestudy.banking.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.casestudy.banking.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    @ResponseBody
    public ResponseDTO handleValidationExceptions(CustomValidationException ex) {
        ResponseDTO responseDTO = new ResponseDTO();

        StringBuilder comment = new StringBuilder();
        comment.append("User info cannot be saved because");
        ex.getConstraintViolations().forEach(violation -> {
            comment.append(" [" + violation.getPropertyPath().toString() + "],");
        });
        comment.append(
                "cannot be null or blank. Please retrigger account details with the mentioned mandatory fields!");

        String msg = comment.toString();
        responseDTO.setSuccess(false);
        responseDTO.setResponseTime(LocalDateTime.now());
        responseDTO.setErrorMessage(Arrays.asList(msg));
        responseDTO.setMessage("FAILED");
        return responseDTO;
    }

}
