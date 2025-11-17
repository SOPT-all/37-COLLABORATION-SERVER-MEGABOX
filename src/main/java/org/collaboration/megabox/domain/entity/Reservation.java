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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    indexes = {
        @Index(name = "idx_reservation_member_id", columnList = "member_id"),
        @Index(name = "idx_reservation_showtime_id", columnList = "showtime_id")
    }
)
@Comment("예매 정보")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예매 PK")
    private Long reservationId;

    @Comment("예매 인원 수")
    private Integer numberOfPeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "showtime_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("상영 정보")
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    @Comment("회원")
    private Member member;

    @Builder
    private Reservation(Showtime showtime, Member member, int numberOfPeople) {
        this.showtime = showtime;
        this.member = member;
        this.numberOfPeople = numberOfPeople;
    }

    public static Reservation create(Showtime showtime, Member member, int numberOfPeople) {
        if (!showtime.hasEnoughSeats(numberOfPeople)) {
            throw new CustomException(ErrorCode.INSUFFICIENT_SEATS);
        }
        showtime.decreaseSeats(numberOfPeople);
        return new Reservation(showtime, member, numberOfPeople);
        return Reservation.builder()
                .showtime(showtime)
                .member(member)
                .numberOfPeople(numberOfPeople)
                .build();
    }
}
