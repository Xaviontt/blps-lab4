package com.itmo.blps.exceptions;

public class NoAvailableGradesException extends RuntimeException {
    public NoAvailableGradesException(){}
    public NoAvailableGradesException(String message) {
        super(message);
    }
}
