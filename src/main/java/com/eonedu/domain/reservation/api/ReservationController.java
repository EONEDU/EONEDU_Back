package com.eonedu.domain.reservation.api;

import com.eonedu.domain.reservation.application.ReservationService;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import com.eonedu.domain.reservation.dto.response.ClientReservationCreateResponse;
import com.eonedu.domain.reservation.dto.response.ReservationByDateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @GetMapping("/v1/reservations")
    public ReservationByDateResponse getReservationByDate(@RequestParam Long branchId,
                                                           @RequestParam Long counselTypeId,
                                                           @RequestParam LocalDate date){
            List<Reservation> reservations = reservationService.findExistedReservation(branchId, counselTypeId, date);
            return new ReservationByDateResponse(reservations);
    }

    @PostMapping("/v1/reservations")
    public ClientReservationCreateResponse createClientReservation(@RequestBody ClientReservationCreateRequest request){
        ClientReservation reservation = reservationService.createClientReservation(request);

        return new ClientReservationCreateResponse(reservation);
    }

//    @PutMapping("/v1/reservations/{reservation_id}")
//    public ClientReservationUpdateResponse updateReservation(@PathVariable("reservation_id") Long reservationId,
//                                                      @RequestBody ClientReservationUpdateRequest request){
//        ClientReservation reservation = reservationService.updateReservation(reservationId, request);
//
//        return new ClientReservationUpdateResponse(reservation);
//    }

    @DeleteMapping("/v1/reservations/{reservationUuid}")
    public String deleteReservation(@PathVariable String reservationUuid){
        reservationService.cancelClientReservation(reservationUuid);
        return "Reservation deleted";
    }
}
