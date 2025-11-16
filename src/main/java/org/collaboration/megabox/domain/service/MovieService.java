package org.collaboration.megabox.domain.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.MovieListResponse;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieListResponse getList() {
        List<Movie> movies = movieRepository.findAllByOrderByMovieIdAsc();
        return MovieListResponse.from(movies);
    }
}