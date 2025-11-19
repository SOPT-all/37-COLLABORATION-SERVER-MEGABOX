package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}