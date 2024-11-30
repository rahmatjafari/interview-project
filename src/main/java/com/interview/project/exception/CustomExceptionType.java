package com.interview.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public enum CustomExceptionType {

    INVALID_INPUT_FIELDS("One or more fields are invalid", HttpStatus.BAD_REQUEST),
    PAIRS_NOT_FOUND("No pairs found.", HttpStatus.NOT_FOUND),
    ;
    private final String message;
    private final HttpStatus httpStatus;

    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .message(message)
                .timestamp(LocalDateTime.now()).build();
    }
}
