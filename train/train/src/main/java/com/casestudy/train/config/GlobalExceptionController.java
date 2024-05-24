package com.casestudy.train.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.casestudy.train.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    @ResponseBody
    public ResponseDTO handleValidationExceptions(CustomValidationException ex) {
        ResponseDTO responseDTO = new ResponseDTO();

        StringBuilder comment = new StringBuilder();
        comment.append("Train info cannot be saved because");
        ex.getConstraintViolations().forEach(violation -> {
            comment.append(" [" + violation.getPropertyPath().toString() + "],");
        });
        // Remove the trailing comma
        comment.deleteCharAt(comment.length() - 1);
        comment.append(
                " cannot be null or blank. Please retrigger train details with the mentioned mandatory fields!");

        String msg = comment.toString();
        responseDTO.setSuccess(false);
        responseDTO.setResponseTime(LocalDateTime.now());
        responseDTO.setErrorMessage(Arrays.asList(msg));
        responseDTO.setMessage("FAILED");
        return responseDTO;
    }

}
