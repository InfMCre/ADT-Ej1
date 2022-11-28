package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "There is a constraint error")
public class NotFoundConstraintException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotFoundConstraintException(String errorMessage) {
        super(errorMessage);
    }
}
