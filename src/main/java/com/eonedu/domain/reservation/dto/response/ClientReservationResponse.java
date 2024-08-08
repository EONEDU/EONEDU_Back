package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.ClientReservation;

import java.time.LocalDate;

public record ClientReservationResponse (
    String reservationUuid,
    String counselTypeName,
    String branchName,
    String clientName,
    String clientPhone,
    LocalDate date,
    String time
){
    public static ClientReservationResponse from(ClientReservation reservation){
        return new ClientReservationResponse(
                reservation.getReservationRandomId(),
                reservation.getCounselType().getName(),
                reservation.getBranch().getName(),
                reservation.getClientName(),
                reservation.getClientPhone(),
                reservation.getDate(),
                reservation.getTime().getTimeString());
    }
}
