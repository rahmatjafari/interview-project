package com.interview.project.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final CustomExceptionType exceptionType;

    public CustomException(CustomExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
