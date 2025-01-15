package com.project.bookreview.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ReviewException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ReviewException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
