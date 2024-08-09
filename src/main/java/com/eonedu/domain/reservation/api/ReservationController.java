package com.eonedu.domain.reservation.api;

import com.eonedu.domain.reservation.application.ReservationService;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import com.eonedu.domain.reservation.dto.request.ClientReservationRequest;
import com.eonedu.domain.reservation.dto.response.ClientReservationResponse;
import com.eonedu.domain.reservation.dto.response.ReservationByDateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // 이미 존재하는 상담 예약 조회하는 API (날짜, 지점, 상담유형)
    @GetMapping("/v1/reservations")
    public ReservationByDateResponse findExistingReservation(@RequestParam Long branchId,
                                                           @RequestParam Long counselTypeId,
                                                           @RequestParam LocalDate date){
            List<Reservation> reservations = reservationService.findExistedReservation(branchId, counselTypeId, date);
            return ReservationByDateResponse.from(reservations);
    }

    // 상담 예약 생성하는 API
    @PostMapping("/v1/reservations")
    public ClientReservationResponse createClientReservation(@Valid @RequestBody ClientReservationCreateRequest request){
        ClientReservation reservation = reservationService.createClientReservation(request);

        return ClientReservationResponse.from(reservation);
    }

    // uuid, 고객 이름, 전화번호 이용해 상담 예약 조회하는 API
    @PostMapping("/v1/reservations/{reservationUuid}")
    public ClientReservationResponse getClientReservation(@PathVariable String reservationUuid,
                                                        @Valid @RequestBody ClientReservationRequest request){
        ClientReservation reservation = reservationService.findClientReservation(reservationUuid, request.clientName(), request.clientPhone());
        return ClientReservationResponse.from(reservation);
    }

    // uuid, 고객 이름, 전화번호 이용해 상담 예약 삭제하는 API
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/v1/reservations/{reservationUuid}/delete")
    public void deleteReservation(@PathVariable String reservationUuid,
                                    @Valid @RequestBody ClientReservationRequest request){

        reservationService.cancelClientReservation(reservationUuid, request.clientName(), request.clientPhone());
    }
}
