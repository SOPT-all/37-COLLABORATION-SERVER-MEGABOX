package org.collaboration.megabox.domain.service;

import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ReviewListResponse;
import org.collaboration.megabox.domain.dto.response.ReviewResponse;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Review;
import org.collaboration.megabox.domain.repository.MovieRepository;
import org.collaboration.megabox.domain.repository.ReviewRepository;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewListResponse getReviewsByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MOVIE));

        List<Review> reviews = reviewRepository.findAllByMovie_MovieIdOrderByCreatedAtDesc(movieId);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(ReviewResponse::from)
                .toList();

        return ReviewListResponse.of(movieId, reviewResponses);
    }
}