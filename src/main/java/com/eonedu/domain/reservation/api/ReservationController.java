package com.eonedu.domain.reservation.api;

import com.eonedu.domain.reservation.application.ReservationService;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import com.eonedu.domain.reservation.dto.request.ClientReservationReadRequest;
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
    @GetMapping("/v1/reservations")
    public ReservationByDateResponse getReservationByDate(@RequestParam Long branchId,
                                                           @RequestParam Long counselTypeId,
                                                           @RequestParam LocalDate date){
            List<Reservation> reservations = reservationService.findExistedReservation(branchId, counselTypeId, date);
            return new ReservationByDateResponse(reservations);
    }

    @PostMapping("/v1/reservations/{reservationUuid}")
    public ClientReservationResponse getClientReservation(@PathVariable String reservationUuid,
                                                        @RequestBody ClientReservationReadRequest request){
        ClientReservation reservation = reservationService.findClientReservation(reservationUuid, request.getClientName(), request.getClientPhone());
        return new ClientReservationResponse(reservation);
    }

    @PostMapping("/v1/reservations")
    public ClientReservationResponse createClientReservation(@RequestBody ClientReservationCreateRequest request){
        ClientReservation reservation = reservationService.createClientReservation(request);

        return new ClientReservationResponse(reservation);
    }

//    @PutMapping("/v1/reservations/{reservation_id}")
//    public ClientReservationUpdateResponse updateReservation(@PathVariable("reservation_id") Long reservationId,
//                                                      @RequestBody ClientReservationUpdateRequest request){
//        ClientReservation reservation = reservationService.updateReservation(reservationId, request);
//
//        return new ClientReservationUpdateResponse(reservation);
//    }

    @PostMapping("/v1/reservations/{reservationUuid}/delete")
    public String deleteReservation(@PathVariable String reservationUuid,
                                    @RequestBody ClientReservationReadRequest request){

        reservationService.cancelClientReservation(reservationUuid, request.getClientName(), request.getClientPhone());
        return "Reservation deleted";
    }
}
