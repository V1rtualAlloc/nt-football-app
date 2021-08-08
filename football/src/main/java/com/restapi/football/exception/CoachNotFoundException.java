package com.restapi.football.exception;

public class CoachNotFoundException extends RuntimeException {
    public CoachNotFoundException(Long id) {
        super("Could not find coach " + id);
    }
}