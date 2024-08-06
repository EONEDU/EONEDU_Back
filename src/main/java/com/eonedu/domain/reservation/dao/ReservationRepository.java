package com.eonedu.domain.reservation.dao;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.domain.ReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByDate(LocalDate date);

    public boolean existsByDateAndTimeAndBranchAndCounselType(LocalDate date, ReservationTime time, Branch branch, CounselType counselType);
}
