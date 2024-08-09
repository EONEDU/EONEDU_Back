package com.eonedu.domain.reservation.dao;

import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientReservationRepository extends JpaRepository<ClientReservation, Long>{
    public List<Reservation> findByClientPhone(String clientPhone);

    public Optional<ClientReservation> findByReservationRandomId(String reservationRandomId);
}
