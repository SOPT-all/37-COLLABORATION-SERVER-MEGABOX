package org.collaboration.megabox.domain.dto.response;

import java.time.LocalDate;
import org.collaboration.megabox.domain.entity.Movie;

public record MovieDetailResponse(
    Long id,
    String title,
    String tag,
    Integer ageRating,
    LocalDate releaseDate,
    Integer runningTimeMinutes,
    Integer rank,
    Float rating,
    Float marketShare,
    Long totalAudience,
    String description,
    String summary
) {
    public static MovieDetailResponse from(Movie movie) {
        return new MovieDetailResponse(
            movie.getMovieId(),
            movie.getTitle(),
            movie.getTag(),
            movie.getAgeRating(),
            movie.getReleaseDate(),
            movie.getRunningTimeMinutes(),
            movie.getMovieRank(),
            movie.getRating(),
            movie.getMarketShare(),
            movie.getTotalAudience(),
            movie.getDescription(),
            movie.getSummary()
        );
    }
}
