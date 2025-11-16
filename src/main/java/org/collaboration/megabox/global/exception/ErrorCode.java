package org.collaboration.megabox.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* ========== 공통 ========== */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ISE", "서버 내부 오류입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FBD", "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNA", "인증되지 않았습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "BR", "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NF", "존재하지 않는 리소스입니다."),

    /* ========== 도메인 ========== */
    NOT_FOUND_MOVIE(HttpStatus.NOT_FOUND, "NOT_FOUND_MOVIE", "존재하지 않는 영화입니다."),
    NOT_FOUND_SHOWTIME(HttpStatus.NOT_FOUND, "NOT_FOUND_SHOWTIME", "존재하지 않는 상영정보입니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "NOT_FOUND_MEMBER", "존재하지 않는 회원입니다."),
    INSUFFICIENT_SEATS(HttpStatus.BAD_REQUEST, "INSUFFICIENT_SEATS", "남은 좌석이 부족합니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}