package org.collaboration.megabox.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReservationCreateRequest(
        @NotNull(message = "예약 인원 지정은 필수입니다.")
        @Min(1)
        Integer numOfPeople
) {
}