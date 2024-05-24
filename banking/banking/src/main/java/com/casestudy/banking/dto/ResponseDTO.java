package com.casestudy.banking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseDTO {

    private String message;
    private List<String> errorMessage;
    private LocalDateTime responseTime;
    private Boolean success;
    private String successMessage;

    public ResponseDTO() {
    }

    public ResponseDTO(Boolean success) {
        this.success = success;
    }

    public ResponseDTO(String message, List<String> errorMessage, LocalDateTime responseTime, Boolean success,
            String successMessage) {
        this.message = message;
        this.errorMessage = errorMessage;
        this.responseTime = responseTime;
        this.success = success;
        this.successMessage = successMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
        result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseDTO other = (ResponseDTO) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (errorMessage == null) {
            if (other.errorMessage != null)
                return false;
        } else if (!errorMessage.equals(other.errorMessage))
            return false;
        if (responseTime == null) {
            if (other.responseTime != null)
                return false;
        } else if (!responseTime.equals(other.responseTime))
            return false;
        return true;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
