package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ShowtimeBeforeReservationResponse;
import org.collaboration.megabox.domain.service.ShowtimeService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상영정보 API", description = "상영정보 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/showtimes/{showtimeId}")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Operation(
            summary = "예매 전 상영정보 조회",
            description = """
            영화 예매 전 상영정보를 조회하는 API입니다.
            - Path Variable로 showtimeId(상영정보 ID)를 전달합니다.
            """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<ShowtimeBeforeReservationResponse>> getShowtimeBeforeReservation(
            @PathVariable("showtimeId") Long showtimeId
    ) {
        ShowtimeBeforeReservationResponse response = showtimeService.getShowtimeBeforeReservation(showtimeId);
        return ApiResponse.success(response);
    }
}