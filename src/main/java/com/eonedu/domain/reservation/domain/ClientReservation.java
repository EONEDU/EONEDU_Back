package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.global.util.RandomIdMaker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class ClientReservation extends Reservation{
    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhone;

    @Column(unique = true, nullable = false)
    private final String reservationRandomId = RandomIdMaker.makeTwelveRandomId();

    @Column(nullable = false)
    private ReservationState state;

    @Builder
    public ClientReservation(Long id, CounselType counselType, Branch branch, LocalDate date, ReservationTime time,  String clientName, String clientPhone) {
        this.id = id;
        this.counselType = counselType;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.state = ReservationState.WAITING;
    }
}
