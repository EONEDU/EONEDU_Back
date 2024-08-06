package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.ClientReservation;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClientReservationCreateResponse {
    private final String reservationUuid;
    private final String counselTypeName;
    private final String branchName;
    private final String clientName;
    private final String clientPhone;
    private final LocalDate date;
    private final String time;

    public ClientReservationCreateResponse(ClientReservation reservation){
        this.reservationUuid = reservation.getReservationRandomId();
        this.counselTypeName = reservation.getCounselType().getName();
        this.branchName = reservation.getBranch().getName();
        this.clientName = reservation.getClientName();
        this.clientPhone = reservation.getClientPhone();
        this.date = reservation.getDate();
        this.time = reservation.getTime().getTimeString();
    }
}
