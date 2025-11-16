package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.MovieListResponse;
import org.collaboration.megabox.domain.service.MovieService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Operation(
        summary = "영화 목록 조회",
        description = """
            등록된 영화들의 기본 정보를 조회하는 API입니다.
            - 정렬 기준: 영화 ID 오름차순
            """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<MovieListResponse>> getList() {
        MovieListResponse response = movieService.getList();

        return ApiResponse.success(response);
    }
}