package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepositoryCustom {

    List<Showtime> findShowtimes(
            List<Long> movieIds,
            LocalDate date,
            TimeSlot timeSlot
    );
}