package com.evently.user.exception;

import org.springframework.http.HttpStatus;

import java.net.URI;

public class AuthenticationFailedException extends ApplicationException {

    public AuthenticationFailedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "authentication-failed");
    }
}
