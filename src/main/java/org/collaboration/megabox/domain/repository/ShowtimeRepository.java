package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Query("""
        SELECT s
        FROM Showtime s
            JOIN FETCH s.movie
            JOIN FETCH s.theater t
            JOIN FETCH t.cinema
        WHERE s.showtimeId = :showtimeId
    """)
    Optional<Showtime> findShowtimeInfoById(Long showtimeId);
}