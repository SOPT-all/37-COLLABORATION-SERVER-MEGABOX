package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.MovieDetailResponse;
import org.collaboration.megabox.domain.dto.response.MovieListResponse;
import org.collaboration.megabox.domain.dto.response.ReviewListResponse;
import org.collaboration.megabox.domain.service.MovieService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "영화 API", description = "영화 관련 API입니다.")
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

    @Operation(
        summary = "영화 상세 조회",
        description = """
            선택한 영화의 상세 정보를 조회하는 API입니다.
            - Path Variable로 movieId(영화 ID)를 전달합니다.
            """
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse<MovieDetailResponse>> getDetail(@PathVariable Long movieId) {
        MovieDetailResponse response = movieService.getDetail(movieId);

        return ApiResponse.success(response);
    }

    @Operation(
            summary = "영화 관람평 조회",
            description = """
            선택한 영화의 관람평들을 조회하는 API입니다.
            - Path Variable로 movieId(영화 ID)를 전달합니다.
            """
    )
    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<ApiResponse<ReviewListResponse>> getReviews(@PathVariable Long movieId) {
        ReviewListResponse response = movieService.getReviewsByMovieId(movieId);
        return ApiResponse.success(response);
    }
}