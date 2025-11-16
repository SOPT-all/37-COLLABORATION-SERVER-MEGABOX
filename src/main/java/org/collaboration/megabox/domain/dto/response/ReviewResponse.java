package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        Integer rating,
        Long memberId,
        String nickname,
        String content,
        LocalDateTime createdAt
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getRating(),
                review.getMember().getMemberId(),
                review.getMember().getNickname(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}