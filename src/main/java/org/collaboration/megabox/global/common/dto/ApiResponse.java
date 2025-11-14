package org.collaboration.megabox.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.collaboration.megabox.global.common.enums.Status;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ApiResponse<T> (
    Status status,
    int statusCode,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String errorCode,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String errorName
) {
    // 성공 (데이터 포함)
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(
            new ApiResponse<>(Status.SUCCESS, HttpStatus.OK.value(), data, null, null, null)
        );
    }

    // 성공 (데이터 없음)
    public static ResponseEntity<ApiResponse<Void>> success() {
        return ResponseEntity.ok(
            new ApiResponse<>(Status.SUCCESS, HttpStatus.OK.value(), null, null, null, null)
        );
    }

    // 실패 (검증/요청 오류 - 단순 메시지)
    public static ResponseEntity<ApiResponse<Void>> fail(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>(
                Status.FAIL,
                HttpStatus.BAD_REQUEST.value(),
                null,
                message,
                null,
                null
            ));
    }

    // 에러 (비즈니스/서버)
    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(new ApiResponse<>(
                Status.ERROR,
                errorCode.getHttpStatus().value(),
                null,
                errorCode.getMessage(),
                errorCode.getErrorCode(),
                errorCode.name()
            ));
    }
}