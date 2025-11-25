package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Cinema;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.Theater;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ShowtimeReadResponse(
        List<CinemaResponse> cinemas
) {
    public static ShowtimeReadResponse from(
            Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> grouped
    ) {
        List<CinemaResponse> cinemas = grouped.entrySet().stream()
                .map(CinemaResponse::from)
                .toList();

        return new ShowtimeReadResponse(cinemas);
    }

    public record CinemaResponse(
            String cinemaName,
            List<MovieResponse> movies
    ) {
        public static CinemaResponse from(
                Map.Entry<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> entry
        ) {
            Cinema cinema = entry.getKey();
            List<MovieResponse> movies = entry.getValue().entrySet().stream()
                    .map(MovieResponse::from)
                    .toList();

            return new CinemaResponse(cinema.getName(), movies);
        }
    }

    public record MovieResponse(
            String movieTitle,
            List<TheaterResponse> theaters
    ) {
        public static MovieResponse from(
                Map.Entry<Movie, Map<Theater, List<Showtime>>> entry
        ) {
            Movie movie = entry.getKey();
            List<TheaterResponse> theaters = entry.getValue().entrySet().stream()
                    .map(TheaterResponse::from)
                    .toList();

            return new MovieResponse(movie.getTitle(), theaters);
        }
    }

    public record TheaterResponse(
            String theaterName,
            String screenType,
            List<ShowtimeResponse> showtimes
    ) {
        public static TheaterResponse from(
                Map.Entry<Theater, List<Showtime>> entry
        ) {
            Theater theater = entry.getKey();
            List<ShowtimeResponse> showtimes = entry.getValue().stream()
                    .map(ShowtimeResponse::from)
                    .toList();

            return new TheaterResponse(
                    theater.getName(),
                    theater.getScreenType(),
                    showtimes
            );
        }
    }

    public record ShowtimeResponse(
            Long showtimeId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Integer totalSeatCount,
            Integer availableSeatCount
    ) {
        public static ShowtimeResponse from(Showtime s) {
            return new ShowtimeResponse(
                    s.getShowtimeId(),
                    s.getStartTime(),
                    s.getEndTime(),
                    s.getTheater().getSeatCount(),
                    s.getLeftSeatCount()
            );
        }
    }
}