package org.collaboration.megabox.domain.controller;

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

    /**
     * 영화 목록 조회 API
     */
    @GetMapping
    public ResponseEntity<ApiResponse<MovieListResponse>> getList() {
        MovieListResponse response = movieService.getList();

        return ApiResponse.success(response);
    }
}