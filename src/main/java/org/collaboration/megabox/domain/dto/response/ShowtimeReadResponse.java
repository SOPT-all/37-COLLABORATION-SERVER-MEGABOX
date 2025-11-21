package org.collaboration.megabox.domain.dto.response;

import org.collaboration.megabox.domain.entity.TimeSlot;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record ShowtimeReadResponse(
        List<Long> movieIds,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        TimeSlot timeSlot
) { }