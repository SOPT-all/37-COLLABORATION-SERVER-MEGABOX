package org.collaboration.megabox.domain.service;

import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.ReservationCreateResponse;
import org.collaboration.megabox.domain.entity.Member;
import org.collaboration.megabox.domain.entity.Reservation;
import org.collaboration.megabox.domain.entity.Showtime;
import org.collaboration.megabox.domain.repository.MemberRepository;
import org.collaboration.megabox.domain.repository.ReservationRepository;
import org.collaboration.megabox.domain.repository.ShowtimeRepository;
import org.collaboration.megabox.global.exception.CustomException;
import org.collaboration.megabox.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ShowtimeRepository showtimeRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationCreateResponse createReservation(Long memberId, Long showtimeId, int numOfPeople) {
        Showtime showtime = showtimeRepository.findByIdWithLock(showtimeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOWTIME));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Reservation reservation = Reservation.create(showtime, member, numOfPeople);

        reservationRepository.save(reservation);
        return ReservationCreateResponse.from(reservation);
    }
}
