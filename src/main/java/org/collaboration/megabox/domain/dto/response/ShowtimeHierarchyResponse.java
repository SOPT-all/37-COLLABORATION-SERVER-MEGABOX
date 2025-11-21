package org.collaboration.megabox.domain.dto.response;


import com.fasterxml.jackson.annotation.JsonValue;
import org.collaboration.megabox.domain.entity.Cinema;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.Theater;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ShowtimeHierarchyResponse(
        CinemaListResponse cinemas
) {

    public static ShowtimeHierarchyResponse from(
            Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> grouped
    ) {
        return new ShowtimeHierarchyResponse(
                CinemaListResponse.from(grouped)
        );
    }

    public record CinemaListResponse(
            @JsonValue
            List<CinemaResponse> cinemas
    ) {
        public static CinemaListResponse from(
                Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> grouped
        ) {
            return new CinemaListResponse(
                    grouped.entrySet().stream()
                            .map(CinemaResponse::from)
                            .toList()
            );
        }
    }

    public record CinemaResponse(
            String cinemaName,
            MovieListResponse movies
    ) {
        public static CinemaResponse from(
                Map.Entry<Cinema, Map<Movie, Map<Theater, List<Showtime>>>> entry
        ) {
            Cinema cinema = entry.getKey();

            return new CinemaResponse(
                    cinema.getName(),
                    MovieListResponse.from(entry.getValue())
            );
        }
    }

    public record MovieListResponse(
            @JsonValue
            List<MovieResponse> movies
    ) {
        public static MovieListResponse from(
                Map<Movie, Map<Theater, List<Showtime>>> movieMap
        ) {
            return new MovieListResponse(
                    movieMap.entrySet().stream()
                            .map(MovieResponse::from)
                            .toList()
            );
        }
    }

    public record MovieResponse(
            String movieTitle,
            TheaterListResponse theaters
    ) {
        public static MovieResponse from(
                Map.Entry<Movie, Map<Theater, List<Showtime>>> entry
        ) {
            Movie movie = entry.getKey();

            return new MovieResponse(
                    movie.getTitle(),
                    TheaterListResponse.from(entry.getValue())
            );
        }
    }

    public record TheaterListResponse(
            @JsonValue
            List<TheaterResponse> items
    ) {
        public static TheaterListResponse from(
                Map<Theater, List<Showtime>> theaterMap
        ) {
            return new TheaterListResponse(
                    theaterMap.entrySet().stream()
                            .map(TheaterResponse::from)
                            .toList()
            );
        }
    }

    public record TheaterResponse(
            String theaterName,
            String screenType,
            ShowtimeListResponse showtimes
    ) {
        public static TheaterResponse from(
                Map.Entry<Theater, List<Showtime>> entry
        ) {
            Theater theater = entry.getKey();

            return new TheaterResponse(
                    theater.getName(),
                    theater.getScreenType(),
                    ShowtimeListResponse.from(entry.getValue())
            );
        }
    }

    public record ShowtimeListResponse(
            @JsonValue
            List<ShowtimeResponse> items
    ) {
        public static ShowtimeListResponse from(List<Showtime> list) {
            return new ShowtimeListResponse(
                    list.stream()
                            .map(ShowtimeResponse::from)
                            .toList()
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