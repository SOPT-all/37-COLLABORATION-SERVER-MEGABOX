package org.collaboration.megabox.domain.entity;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    indexes = {
        @Index(name = "idx_showtime_theater_id", columnList = "theater_id"),
        @Index(name = "idx_showtime_movie_id", columnList = "movie_id")
    }
)
@Comment("상영 시간 정보")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상영 정보 PK")
    private Long showtimeId;

    @Comment("남은 좌석 수")
    private Integer leftSeatCount;

    @Comment("상영 시작 시간")
    private LocalDateTime startTime;

    @Comment("상영 종료 시간")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "theater_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("상영관")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "movie_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("영화")
    private Movie movie;
}
