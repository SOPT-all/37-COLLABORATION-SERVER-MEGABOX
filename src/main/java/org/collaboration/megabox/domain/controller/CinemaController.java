package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.CinemaListResponse;
import org.collaboration.megabox.domain.service.CinemaService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "영화관 API", description = "영화관 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    @Operation(
            summary = "영화 영화관 리스트 조회",
            description = """
            선택한 영화들의 영화관을 조회하는 API입니다.
            - RequestParam으로 movieIds(영화 ID 리스트)를 전달합니다.
            """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<CinemaListResponse>> getCinemas(
            @RequestParam("movieIds")List<Long> movieIds
    ) {
        CinemaListResponse response = cinemaService.getCinemasByMovies(movieIds);
        return ApiResponse.success(response);
    }
}