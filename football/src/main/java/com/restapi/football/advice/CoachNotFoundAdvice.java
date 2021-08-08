package com.restapi.football.advice;

import com.restapi.football.exception.CoachNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CoachNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CoachNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String coachNotFoundHandler(CoachNotFoundException ex) {
        return ex.getMessage();
    }
}
