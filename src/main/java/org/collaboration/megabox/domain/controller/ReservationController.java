package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.request.ReservationCreateRequest;
import org.collaboration.megabox.domain.dto.response.ReservationCreateResponse;
import org.collaboration.megabox.domain.service.ReservationService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "예매 API", description = "영화 예매 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/showtimes/{showtimeId}/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(
            summary = "영화 예매",
            description = """
            영화 예매 정보를 생성하는 API입니다.
            - Path Variable로 showtimeId(상영정보 ID)를 전달합니다.
            - RequestBody로 memberId(예약자 ID), numOfPeople(예약 좌석수)를 전달합니다. 
            """
    )
    @PostMapping
    public ResponseEntity<ApiResponse<ReservationCreateResponse>> createReservation(
            @PathVariable("showtimeId") Long showtimeId,
            @RequestBody @Valid ReservationCreateRequest request
            ) {
        ReservationCreateResponse response = reservationService.createReservation(showtimeId, request);
        return ApiResponse.success(response);
    }
}
