package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Review;
import java.time.LocalDateTime;
import java.util.List;

public record ReviewListResponse(
        Integer reviewCount,
        List<ReviewResponse> reviews
) {
    public static ReviewListResponse of(int reviewCount, List<ReviewResponse> reviews) {
        return new ReviewListResponse(reviewCount, reviews);
    }

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

        public static List<ReviewResponse> from(List<Review> reviews) {
            return reviews.stream()
                    .map(ReviewResponse::from)
                    .toList();
        }
    }
}