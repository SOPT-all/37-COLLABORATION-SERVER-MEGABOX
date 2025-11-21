package org.collaboration.megabox.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.entity.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShowtimeRepositoryCustomImpl implements ShowtimeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Showtime> findShowtimes(List<Long> movieIds, LocalDate date, TimeSlot timeSlot) {
        QShowtime s = QShowtime.showtime;
        QMovie m = QMovie.movie;
        QTheater t = QTheater.theater;
        QCinema c = QCinema.cinema;

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59);

        BooleanBuilder builder = new BooleanBuilder();

        // 영화 선택 (선택 안했을시 모든 영화 조회)
        if (movieIds != null && !movieIds.isEmpty()) {
            builder.and(m.movieId.in(movieIds));
        }

        // 날짜 선택
        builder.and(s.startTime.between(start, end));

        // 시간대 선택
        if (timeSlot != null) {
            builder.and(timeSlot.toPredicate(s.startTime));
        }

        return query.select(s)
                .from(s)
                .join(s.movie, m).fetchJoin()
                .join(s.theater, t).fetchJoin()
                .join(t.cinema, c).fetchJoin()
                .where(builder)
                .fetch();
    }
}