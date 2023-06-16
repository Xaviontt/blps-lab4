package com.itmo.blps.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(){}
    public InvalidDataException(String message) {
        super(message);
    }
}
