package com.itmo.blps.exceptions;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(){}
    public AuthenticationFailException(String message) {
        super(message);
    }
}
