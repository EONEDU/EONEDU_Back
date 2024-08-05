package com.eonedu.domain.reservation.dao;

import com.eonedu.domain.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByDate(LocalDate date);
}
