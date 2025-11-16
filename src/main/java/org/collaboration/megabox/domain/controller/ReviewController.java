package org.collaboration.megabox.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ReviewListResponse;
import org.collaboration.megabox.domain.service.ReviewService;
import org.collaboration.megabox.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관람평 API", description = "관람평 관련 API입니다.")
@RestController
@RequestMapping("/api/v1/movies/{movieId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "영화 관람평 조회",
            description = """
            선택한 영화의 관람평들을 조회하는 API입니다.
            - Path Variable로 movieId(영화 ID)를 전달합니다.
            """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<ReviewListResponse>> getReviews(@PathVariable Long movieId) {
        ReviewListResponse response = reviewService.getReviewsByMovieId(movieId);
        return ApiResponse.success(response);
    }
}