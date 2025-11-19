package org.collaboration.megabox.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.RecentDatesResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DateService {

    public RecentDatesResponse getRecent7Days() {
        LocalDate today = LocalDate.now();

        List<LocalDate> dates = IntStream.rangeClosed(0, 6)
            .mapToObj(today::plusDays)
            .collect(Collectors.toList());

        return RecentDatesResponse.from(dates);
    }
}