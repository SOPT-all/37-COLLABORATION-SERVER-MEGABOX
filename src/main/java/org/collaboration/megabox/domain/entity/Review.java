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
        @Index(name = "idx_review_member_id", columnList = "member_id"),
        @Index(name = "idx_review_movie_id", columnList = "movie_id")
    }
)
@Comment("리뷰 정보")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("리뷰 PK")
    private Long reviewId;

    @Comment("리뷰 내용")
    private String content;

    @Comment("리뷰 별점")
    private Integer rating;

    @Comment("작성일시")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("작성 회원")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "movie_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("리뷰 대상 영화")
    private Movie movie;
}
