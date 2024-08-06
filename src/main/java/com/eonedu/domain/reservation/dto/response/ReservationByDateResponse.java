package com.eonedu.domain.reservation.dto.response;

import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.domain.ReservationTime;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReservationByDateResponse {
    private final List<String> reservations;

    public ReservationByDateResponse(List<Reservation> reservations) {
        this.reservations = reservations.stream()
                .map(Reservation::getTime)
                .map(ReservationTime::getTimeString)
                .collect(Collectors.toList());
    }

//    public ReservationByDateResponse(List<Reservation> reservations) {
//        this.reservations = reservations.stream()
//                .map(ReservationDto::new)
//                .collect(Collectors.toList());
//    }

//    @Getter
//    public static class ReservationDto{
//        private final Long id;
//        private final LocalDate date;
//        private final LocalTime time;
//        private final String counselType;
//        private final String branch;
//
//        public ReservationDto(Reservation reservation) {
//            this.id = reservation.getId();
//            this.date = reservation.getDate();
//            this.time = reservation.getStartTime();
//            this.counselType = reservation.getCounselType().getName();
//            this.branch = reservation.getBranch().getName();
//        }
//    }
}
