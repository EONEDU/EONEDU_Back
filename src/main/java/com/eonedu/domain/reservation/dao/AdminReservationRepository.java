package com.eonedu.domain.reservation.dao;

import com.eonedu.domain.reservation.domain.AdminReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReservationRepository extends JpaRepository<AdminReservation, Long> {
}
