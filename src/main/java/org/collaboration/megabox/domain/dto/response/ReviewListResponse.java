package org.collaboration.megabox.domain.dto.response;

import java.util.List;

public record ReviewListResponse(
        Long movieId,
        Integer reviewCount,
        List<ReviewResponse> reviews
) {
    public static ReviewListResponse of(Long movieId, List<ReviewResponse> reviews) {
        return new ReviewListResponse(movieId, reviews.size(), reviews);
    }
}