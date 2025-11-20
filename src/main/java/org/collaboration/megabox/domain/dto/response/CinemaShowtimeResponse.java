package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Cinema;
import org.collaboration.megabox.domain.entity.Movie;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.entity.Theater;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record CinemaShowtimeResponse(
        List<CinemaResponse> cinemas
) {
    public static CinemaShowtimeResponse from(List<Showtime> showtimes) {
        List<Showtime> sorted = showtimes.stream()
                .sorted(Comparator.comparing(
                            (Showtime s) -> s.getTheater().getCinema().getName()
                        ).thenComparing(s -> s.getMovie().getMovieRank())
                        .thenComparing(s -> s.getTheater().getName())
                        .thenComparing(Showtime::getStartTime)
                ).toList();

        List<CinemaResponse> cinemas = sorted.stream()
                // 그룹핑: 영화관 -> 영화 -> 상영관 (LinkedHashMap을 이용한 정렬 유지)
                .collect(Collectors.groupingBy(
                        s -> s.getTheater().getCinema(),
                        LinkedHashMap::new,
                        Collectors.groupingBy(
                                Showtime::getMovie,
                                LinkedHashMap::new,
                                Collectors.groupingBy(
                                        Showtime::getTheater,
                                        LinkedHashMap::new,
                                        Collectors.toList()
                                )
                        )
                ))
                .entrySet().stream()
                .map(CinemaResponse::from)
                .collect(Collectors.toList());

        return new CinemaShowtimeResponse(cinemas);
    }

    public record CinemaResponse(
            String cinemaName,
            List<MovieResponse> movies
    ) {
        public static CinemaResponse from(Map.Entry<Cinema, ? extends Map<Movie, ? extends Map<Theater, List<Showtime>>>> cinemaEntry) {
            List<MovieResponse> movies = cinemaEntry.getValue().entrySet().stream()
                    .map(MovieResponse::from)
                    .collect(Collectors.toList());
            return new CinemaResponse(cinemaEntry.getKey().getName(), movies);
        }

        public record MovieResponse(
                String movieTitle,
                List<TheaterResponse> theaters
        ) {
            public static MovieResponse from(Map.Entry<Movie, ? extends Map<Theater, List<Showtime>>> movieEntry) {
                List<TheaterResponse> theaters = movieEntry.getValue().entrySet().stream()
                        .map(TheaterResponse::from)
                        .collect(Collectors.toList());
                return new MovieResponse(movieEntry.getKey().getTitle(), theaters);
            }

            public record TheaterResponse(
                    String theaterName,
                    String screenType,
                    List<ShowtimeResponse> showtimes
            ) {
                public static TheaterResponse from(Map.Entry<Theater, ? extends List<Showtime>>  theaterEntry) {
                    Theater theater = theaterEntry.getKey();
                    List<ShowtimeResponse> showtimes = theaterEntry.getValue().stream()
                            .map(showtime -> ShowtimeResponse.from(showtime, theater))
                            .collect(Collectors.toList());
                    return new TheaterResponse(theater.getName(), theater.getScreenType(), showtimes);
                }

                public record ShowtimeResponse(
                        Long showtimeId,
                        LocalDateTime startTime,
                        LocalDateTime endTime,
                        Integer seatCount,
                        Integer leftSeatCount
                ) {
                    public static ShowtimeResponse from(Showtime showtime, Theater theater) {
                        return new ShowtimeResponse(
                                showtime.getShowtimeId(),
                                showtime.getStartTime(),
                                showtime.getEndTime(),
                                theater.getSeatCount(),
                                showtime.getLeftSeatCount()
                        );
                    }
                }
            }
        }
    }
}
