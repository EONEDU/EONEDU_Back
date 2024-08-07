package com.eonedu.domain.reservation.api;

import com.eonedu.domain.reservation.application.ReservationService;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import com.eonedu.domain.reservation.dto.request.RequestClientInformation;
import com.eonedu.domain.reservation.dto.response.ClientReservationResponse;
import com.eonedu.domain.reservation.dto.response.ReservationByDateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // 이미 존재하는 상담 예약 조회하는 API (날짜, 지점, 상담유형)
    @GetMapping("/v1/reservations")
    public ReservationByDateResponse getReservationByDate(@RequestParam Long branchId,
                                                           @RequestParam Long counselTypeId,
                                                           @RequestParam LocalDate date){
            List<Reservation> reservations = reservationService.findExistedReservation(branchId, counselTypeId, date);
            return new ReservationByDateResponse(reservations);
    }

    // 상담 예약 생성하는 API
    @PostMapping("/v1/reservations")
    public ClientReservationResponse createClientReservation(@RequestBody ClientReservationCreateRequest request){
        ClientReservation reservation = reservationService.createClientReservation(request);

        return new ClientReservationResponse(reservation);
    }

    // uuid, 고객 이름, 전화번호 이용해 상담 예약 조회하는 API
    @PostMapping("/v1/reservations/{reservationUuid}")
    public ClientReservationResponse getClientReservation(@PathVariable String reservationUuid,
                                                        @RequestBody RequestClientInformation request){
        ClientReservation reservation = reservationService.findClientReservation(reservationUuid, request.getClientName(), request.getClientPhone());
        return new ClientReservationResponse(reservation);
    }

    // uuid, 고객 이름, 전화번호 이용해 상담 예약 삭제하는 API
    @PostMapping("/v1/reservations/{reservationUuid}/delete")
    public String deleteReservation(@PathVariable String reservationUuid,
                                    @RequestBody RequestClientInformation request){

        reservationService.cancelClientReservation(reservationUuid, request.getClientName(), request.getClientPhone());
        return "Reservation deleted";
    }
}
