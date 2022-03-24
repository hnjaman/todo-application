package com.hnj.code.controller.advice;


import com.hnj.code.model.Response.ResponseEnvelope;
import com.hnj.code.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    protected ResponseEntity<ResponseEnvelope> exceptionHandler(AppException ex) {
        String errorMessage = ex.getErrorMessage();
        HttpStatus httpStatus = ex.getHttpStatus();
        log.error("error handled for -- " + errorMessage);
        return new ResponseEntity<>(new ResponseEnvelope<>(ex.getData(), true, errorMessage, httpStatus.value()),
                httpStatus);
    }
}