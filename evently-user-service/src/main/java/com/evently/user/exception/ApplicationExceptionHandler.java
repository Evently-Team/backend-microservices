package com.evently.user.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${problemdetails.type.prefix}")
    private String typePrefix;

    @ExceptionHandler(ApplicationException.class)
    public ProblemDetail handleApplicationException(ApplicationException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(e.getHttpStatus(), e.getMessage());
        problemDetail.setType(URI.create(typePrefix + e.getType()));

        return problemDetail;
    }
}
