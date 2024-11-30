package com.interview.project.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private Map<String, String> payloads;
}
