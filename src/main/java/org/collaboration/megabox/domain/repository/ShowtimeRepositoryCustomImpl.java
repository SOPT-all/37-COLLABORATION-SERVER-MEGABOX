package org.collaboration.megabox.domain.repository;

import com.querydsl.core.BooleanBuilder;
import static com.querydsl.core.group.GroupBy.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.entity.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class ShowtimeRepositoryCustomImpl implements ShowtimeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Map<Cinema, Map<Movie, Map<Theater, List<Showtime>>>>  findShowtimes(List<Long> movieIds, LocalDate date, TimeSlot timeSlot) {
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
                .orderBy(
                        c.name.asc(),
                        m.title.asc(),
                        t.name.asc(),
                        s.startTime.asc()
                )
                .transform(
                        groupBy(c).as(  // 영화관별 그룹핑
                                map(m,  // 영화별 그룹핑
                                        map(t,  // 상영관별 그룹핑
                                                list(s)  // 그룹핑에 맞춰 상영정보 리스트로
                                        )
                                )
                        )
                );
    }
}