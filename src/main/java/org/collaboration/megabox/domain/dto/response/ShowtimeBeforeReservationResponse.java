package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Showtime;

import java.time.LocalDateTime;

public record ShowtimeBeforeReservationResponse(
        Long movieId,
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String cinemaName,
        String theaterName,
        String screenType
) {
    public static ShowtimeBeforeReservationResponse from(Showtime showtime) {
        return new ShowtimeBeforeReservationResponse(
                showtime.getMovie().getMovieId(),
                showtime.getMovie().getTitle(),
                showtime.getStartTime(),
                showtime.getEndTime(),
                showtime.getTheater().getCinema().getName(),
                showtime.getTheater().getName(),
                showtime.getTheater().getScreenType()
        );
    }
}