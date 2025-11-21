package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long>, ShowtimeRepositoryCustom {

    @Query("""
        SELECT s
        FROM Showtime s
            JOIN FETCH s.movie
            JOIN FETCH s.theater t
            JOIN FETCH t.cinema
        WHERE s.showtimeId = :showtimeId
    """)
    Optional<Showtime> findShowtimeInfoById(Long showtimeId);
           
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Showtime s WHERE s.showtimeId = :id")
    Optional<Showtime> findByIdWithLock(Long id);
}