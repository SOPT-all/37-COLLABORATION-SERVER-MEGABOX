package org.collaboration.megabox.domain.service;

import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.CinemaShowtimeResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeBeforeReservationResponse;
import org.collaboration.megabox.domain.dto.response.ShowtimeReadResponse;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.repository.ShowtimeRepository;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public CinemaShowtimeResponse getShowtimes(ShowtimeReadResponse request) {
        List<Showtime> showtimes = showtimeRepository.findShowtimes(
                request.movieIds(),
                request.date(),
                request.timeSlot()
        );
        return CinemaShowtimeResponse.from(showtimes);
    }
}