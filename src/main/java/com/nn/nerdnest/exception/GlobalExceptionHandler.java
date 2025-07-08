package com.nn.nerdnest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 로직 예외
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    /**
     * Bean Validation 예외 (RequestBody)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult().getFieldErrors());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Bean Validation 예외 (ModelAttribute)
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("BindException", e);
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getFieldErrors());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 인증 실패 예외
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 권한 없음 예외
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("AccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    /**
     * 지원하지 않는 HTTP Method 예외
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * IllegalArgumentException 예외
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 모든 예외
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<ErrorResponse.FieldError> getFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .field(error.getField())
                        .value(error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
                        .reason(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
} 