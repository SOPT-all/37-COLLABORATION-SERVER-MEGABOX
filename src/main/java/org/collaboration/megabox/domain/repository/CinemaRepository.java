package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("""
        SELECT DISTINCT c
        FROM Cinema c
            JOIN Theater t ON t.cinema = c
            JOIN Showtime s ON s.theater = t
        WHERE s.movie.movieId IN :movieIds
        ORDER BY c.name ASC
    """)
    List<Cinema> findAllByMovieIds(@Param("movieIds") List<Long> movieIds);
}
