package com.evently.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
