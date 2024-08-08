package com.eonedu.domain.reservation.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "admin_reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminReservation extends Reservation{
    private String adminName;

    private String reason;
}
