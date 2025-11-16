package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Reservation;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.Theater;

import java.time.LocalDateTime;

public record ReservationCreateResponse(
        Long reservationId,
        Integer numOfPeople,
        ShowtimeInfo showtime
) {
    public record ShowtimeInfo(
            Long showtimeId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            MovieInfo movie,
            TheaterInfo theater
    ) {
        public record MovieInfo(
                Long movieId,
                String title
        ) {
            public static MovieInfo from(Movie movie) {
                return new MovieInfo(
                        movie.getMovieId(),
                        movie.getTitle()
                );
            }
        }

        public record TheaterInfo(
                String theaterName,
                String screenType,
                String cinemaName
        ) {
            public static TheaterInfo from(Theater theater) {
                return new TheaterInfo(
                        theater.getName(),
                        theater.getScreenType(),
                        theater.getCinema().getName()
                );
            }
        }

        public static ShowtimeInfo from(Showtime showtime) {
            return new ShowtimeInfo(
                    showtime.getShowtimeId(),
                    showtime.getStartTime(),
                    showtime.getEndTime(),
                    MovieInfo.from(showtime.getMovie()),
                    TheaterInfo.from(showtime.getTheater())
            );
        }
    }
    public static ReservationCreateResponse from(Reservation reservation) {
        return new ReservationCreateResponse(
                reservation.getReservationId(),
                reservation.getNumberOfPeople(),
                ShowtimeInfo.from(reservation.getShowtime())
        );
    }
}