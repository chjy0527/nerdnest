package com.nn.nerdnest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Member 관련 오류
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "M002", "이미 존재하는 이메일입니다."),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "M003", "이미 사용 중인 사용자명입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "M004", "비밀번호가 일치하지 않습니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "M005", "아이디 또는 비밀번호가 잘못되었습니다."),

    // Board 관련 오류
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "게시글을 찾을 수 없습니다."),
    BOARD_ACCESS_DENIED(HttpStatus.FORBIDDEN, "B002", "게시글에 대한 권한이 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "B003", "카테고리를 찾을 수 없습니다."),

    // Comment 관련 오류
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "댓글을 찾을 수 없습니다."),
    COMMENT_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C002", "댓글에 대한 권한이 없습니다."),
    PARENT_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "부모 댓글을 찾을 수 없습니다."),

    // JWT/인증 관련 오류
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "J001", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "J002", "만료된 토큰입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "J003", "인증이 필요합니다."),

    // Job 관련 오류
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "JB001", "존재하지 않는 직업입니다."),

    // 공통 오류
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 내부 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "S002", "허용되지 않은 HTTP 메서드입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "S003", "접근이 거부되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
