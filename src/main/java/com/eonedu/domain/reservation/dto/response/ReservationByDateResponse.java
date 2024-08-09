package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.domain.ReservationTime;

import java.util.List;
import java.util.stream.Collectors;

public record ReservationByDateResponse (List<String> reservations) {
    public static ReservationByDateResponse from(List<Reservation> reservations) {
        return new ReservationByDateResponse(reservations.stream()
                .map(Reservation::getTime)
                .map(ReservationTime::getTimeString)
                .collect(Collectors.toList()));
    }
}
