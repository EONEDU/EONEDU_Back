package com.eonedu.domain.reservation.domain;

import jakarta.persistence.*;

@Entity
public class AdminReservation extends Reservation{
    private String adminName;

    private String reason;
}
