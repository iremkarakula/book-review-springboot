package com.project.bookreview.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookException extends RuntimeException {

    private HttpStatus httpStatus;

    public BookException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
