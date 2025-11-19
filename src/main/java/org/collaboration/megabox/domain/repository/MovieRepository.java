package org.collaboration.megabox.domain.repository;

import java.util.List;
import java.util.Optional;

import org.collaboration.megabox.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    List<Movie> findAllByOrderByMovieIdAsc();

    @Query("""
        SELECT m
        FROM Movie m
        LEFT JOIN FETCH m.reviews
        WHERE m.movieId = :movieId
    """)
    Optional<Movie> getMovieWithReviews(@Param("movieId") Long movieId);
}