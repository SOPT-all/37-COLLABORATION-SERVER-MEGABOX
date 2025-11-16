package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.RecentDatesResponse;
import org.collaboration.megabox.domain.service.DateService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DateController {

    private final DateService dateService;

    @Operation(
        summary = "최근 7일 날짜 조회",
        description = """
            오늘을 포함한 최근 7일의 날짜를 조회하는 API입니다.
            """
    )
    @GetMapping("/recent-dates")
    public ResponseEntity<ApiResponse<RecentDatesResponse>> getRecent7Days() {
        RecentDatesResponse response = dateService.getRecent7Days();

        return ApiResponse.success(response);
    }
}