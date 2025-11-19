package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.Cinema;

import java.util.List;

public record CinemaListResponse(
        List<String> cinemas
) {
    public static CinemaListResponse from(List<Cinema> cinemas) {
        return new CinemaListResponse(
                cinemas.stream()
                        .map(Cinema::getName)
                        .toList()
        );
    }
}