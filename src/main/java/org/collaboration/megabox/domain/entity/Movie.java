package org.collaboration.megabox.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("영화 정보")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("영화 PK")
    private Long movieId;

    @Comment("제목")
    private String title;

    @Comment("태그")
    private String tag;

    @Comment("관람 등급")
    private Integer ageRating;

    @Comment("개봉일(문자열)")
    private LocalDate releaseDate;

    @Comment("상영 시간(분)")
    private Integer runningTimeMinutes;

    @Comment("현재 순위")
    private Integer movieRank;

    @Comment("평점")
    private Float rating;

    @Comment("점유율")
    private Float marketShare;

    @Comment("누적 관객 수")
    private Long totalAudience;

    @Comment("상세 설명")
    private String description;

    @Comment("요약 설명")
    private String summary;
}
