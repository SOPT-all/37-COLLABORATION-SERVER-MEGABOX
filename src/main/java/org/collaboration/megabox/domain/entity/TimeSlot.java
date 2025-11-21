package org.collaboration.megabox.domain.entity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public enum TimeSlot {
    MORNING(6, 11),
    AFTERNOON(12, 17),  // 12 ~ 17
    EVENING(18, 21),  // 18 ~21
    NIGHT(22, 5);  // 22 ~ 05

    private final int start;
    private final int end;

    public BooleanExpression toPredicate(DateTimePath<LocalDateTime> time) {  // TimeSlot이 규칙에 따라 WHERE 조건 생성
        if (this != NIGHT) {
            return time.hour().between(start, end);
        } else {
            return time.hour().goe(start).or(time.hour().loe(end));
        }
    }
}