package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Movie;

import java.util.Optional;

public interface MovieRepositoryCustom {

    Optional<Movie> getMovieWithReviews(Long movieId);
}