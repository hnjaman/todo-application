package com.hnj.code.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends RuntimeException {

    private String errorMessage;
    private HttpStatus httpStatus;
    Object data = new ObjectMapper().createObjectNode();

    public AppException() {
    }

    public AppException(String errorMessage) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorMessage = errorMessage;
    }

    public AppException(String errorMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public AppException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = httpStatus.getReasonPhrase();
    }
}
