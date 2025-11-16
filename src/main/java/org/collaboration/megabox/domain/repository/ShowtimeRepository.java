package org.collaboration.megabox.domain.repository;

import jakarta.persistence.LockModeType;
import org.collaboration.megabox.domain.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Showtime s WHERE s.showtimeId = :id")
    Optional<Showtime> findByIdWithLock(Long id);
}
