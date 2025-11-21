package org.collaboration.megabox.domain.service;

import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ShowtimeBeforeReservationResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeHierarchyResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeReadRequest;
import org.collaboration.megabox.domain.entity.Cinema;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.Theater;
import org.collaboration.megabox.domain.repository.ShowtimeRepository;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeBeforeReservationResponse getShowtimeBeforeReservation(Long showtimeId) {
        Showtime showtime = showtimeRepository.findShowtimeInfoById(showtimeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOWTIME));
        return ShowtimeBeforeReservationResponse.from(showtime);
    }

    public ShowtimeHierarchyResponse getShowtimes(ShowtimeReadRequest request) {
        Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> showtimes = showtimeRepository.findShowtimes(
                request.movieIds(),
                request.date(),
                request.timeSlot()
        );
        return ShowtimeHierarchyResponse.from(showtimes);
    }
}