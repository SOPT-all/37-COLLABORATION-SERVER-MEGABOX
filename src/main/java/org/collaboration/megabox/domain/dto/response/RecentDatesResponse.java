package org.collaboration.megabox.domain.dto.response;

import java.time.LocalDate;
import java.util.List;

public record RecentDatesResponse(
    List<LocalDate> dates
) {
    public static RecentDatesResponse from(List<LocalDate> dates) {
        return new RecentDatesResponse(dates);
    }
}