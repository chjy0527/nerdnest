package com.nn.nerdnest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<FieldError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .status(errorCode.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus().value())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
