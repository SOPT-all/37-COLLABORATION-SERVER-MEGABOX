package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ShowtimeRepositoryCustom {

    Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>>  findShowtimes(
            List<Long> movieIds,
            LocalDate date,
            TimeSlot timeSlot
    );
}