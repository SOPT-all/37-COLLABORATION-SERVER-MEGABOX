package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ShowtimeBeforeReservationResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeHierarchyResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeReadRequest;
import org.collaboration.megabox.domain.service.ShowtimeService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영정보 API", description = "상영정보 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Operation(
            summary = "예매 전 상영정보 조회",
            description = """
            영화 예매 전 상영정보를 조회하는 API입니다.
            - Path Variable로 showtimeId(상영정보 ID)를 전달합니다.
            """
    )
    @GetMapping("/{showtimeId}")
    public ResponseEntity<ApiResponse<ShowtimeBeforeReservationResponse>> getShowtimeBeforeReservation(
            @PathVariable("showtimeId") Long showtimeId
    ) {
        ShowtimeBeforeReservationResponse response = showtimeService.getShowtimeBeforeReservation(showtimeId);
        return ApiResponse.success(response);
    }

    @Operation(
            summary = "상영정보 조회",
            description = """
            선택한 영화들의 상영정보를 조회하는 API입니다.
            - RequestParam으로 movieIds(선택한 영화 Id들), date(선택한 날짜), timeslot(시간대)를 전달합니다.
            """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<ShowtimeHierarchyResponse>> getShowtimes(
            @ModelAttribute ShowtimeReadRequest request
    ) {
        ShowtimeHierarchyResponse response = showtimeService.getShowtimes(request);
        return ApiResponse.success(response);
    }
}