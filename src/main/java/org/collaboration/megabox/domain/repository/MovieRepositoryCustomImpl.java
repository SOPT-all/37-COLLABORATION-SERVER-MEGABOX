package org.collaboration.megabox.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.entity.Movie;
import org.springframework.stereotype.Repository;
import static org.collaboration.megabox.domain.entity.QMovie.movie;
import static org.collaboration.megabox.domain.entity.QReview.review;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Optional<Movie> getMovieWithReviews(Long movieId) {
        return Optional.ofNullable(
                query.selectFrom(movie).distinct()
                        .leftJoin(movie.reviews, review).fetchJoin()
                        .where(movie.movieId.eq(movieId))
                        .orderBy(review.createdAt.desc())
                        .fetchOne()
        );
    }
}