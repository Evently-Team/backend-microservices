package com.evently.user.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

@Getter
public class ApplicationException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;
    private final URI type;

    public ApplicationException(String message, HttpStatus httpStatus, URI type) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.type = type;
    }

    public ApplicationException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, URI.create("about:blank"));
    }

    public ApplicationException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
}
