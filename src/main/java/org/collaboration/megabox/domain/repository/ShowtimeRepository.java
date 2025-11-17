package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}