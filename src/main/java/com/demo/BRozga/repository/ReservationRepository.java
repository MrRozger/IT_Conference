package com.demo.BRozga.repository;

import com.demo.BRozga.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Override
    Optional<Reservation> findById(Long id);

    List<Reservation> findReservationsByParticipantId(Long id);

}
