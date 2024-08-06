package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.ClientReservation;
import lombok.Getter;

import java.time.LocalDate;

@Deprecated
@Getter
public class ClientReservationReadResponse {
    private final String reservationUuid;
    private final String counselTypeName;
    private final String branchName;
    private final LocalDate date;
    private final String time;

    public ClientReservationReadResponse(ClientReservation reservation){
        this.reservationUuid = reservation.getReservationRandomId();
        this.counselTypeName = reservation.getCounselType().getName();
        this.branchName = reservation.getBranch().getName();
        this.date = reservation.getDate();
        this.time = reservation.getTime().getTimeString();
    }
}
