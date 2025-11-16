package org.collaboration.megabox.domain.dto.response;

import java.util.List;
import org.collaboration.megabox.domain.entity.Movie;

public record MovieListResponse(
    List<MovieSummaryResponse> movies
) {

    public static MovieListResponse from(List<Movie> movies) {
        List<MovieSummaryResponse> movieResponses = movies.stream()
            .map(MovieSummaryResponse::from)
            .toList();

        return new MovieListResponse(movieResponses);
    }

    public record MovieSummaryResponse(
        Long id,
        String title,
        String tag
    ) {
        public static MovieSummaryResponse from(Movie movie) {
            return new MovieSummaryResponse(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getTag()
            );
        }
    }
}