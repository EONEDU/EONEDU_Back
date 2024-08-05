package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.ClientReservation;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ClientReservationUpdateResponse {
    private Long id;

    private Long branchId;
    private Long counselTypeId;
    private LocalDate date;
    private LocalTime time;
    private String clientName;
    private String clientPhone;

    public ClientReservationUpdateResponse(ClientReservation reservation) {
        this.id = reservation.getId();
        this.branchId = reservation.getBranch().getId();
        this.counselTypeId = reservation.getCounselType().getId();
        this.date = reservation.getDate();
        this.time = reservation.getStartTime();
        this.clientName = reservation.getClientName();
        this.clientPhone = reservation.getClientPhone();
    }
}
