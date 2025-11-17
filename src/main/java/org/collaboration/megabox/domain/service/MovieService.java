package org.collaboration.megabox.domain.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.MovieDetailResponse;
import org.collaboration.megabox.domain.dto.response.MovieListResponse;
import org.collaboration.megabox.domain.dto.response.ReviewListResponse;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Review;
import org.collaboration.megabox.domain.repository.MovieRepository;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.collaboration.megabox.domain.dto.response.ReviewListResponse.ReviewResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieListResponse getList() {
        List<Movie> movies = movieRepository.findAllByOrderByMovieIdAsc();
        return MovieListResponse.from(movies);
    }

    public MovieDetailResponse getDetail(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MOVIE));

        return MovieDetailResponse.from(movie);
    }

    public ReviewListResponse getReviewsByMovieId(Long movieId) {
        Movie movie = movieRepository.getMovieWithReviews(movieId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MOVIE));

        List<Review> reviews = movie.getReviews();
        List<ReviewResponse> reviewResponses =ReviewResponse.from(reviews);

        return ReviewListResponse.of(reviews.size(), reviewResponses);
    }
}