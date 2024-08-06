package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.ReservationTime;
import lombok.Getter;

import java.time.LocalDate;

@Deprecated
@Getter
public class ClientReservationUpdateResponse {
    private Long id;

    private Long branchId;
    private Long counselTypeId;
    private LocalDate date;
    private ReservationTime time;
    private String clientName;
    private String clientPhone;

    public ClientReservationUpdateResponse(ClientReservation reservation) {
        this.id = reservation.getId();
        this.branchId = reservation.getBranch().getId();
        this.counselTypeId = reservation.getCounselType().getId();
        this.date = reservation.getDate();
        this.time = reservation.getTime();
        this.clientName = reservation.getClientName();
        this.clientPhone = reservation.getClientPhone();
    }
}
