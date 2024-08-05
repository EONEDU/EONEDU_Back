package com.eonedu.domain.reservation.api;

import com.eonedu.domain.reservation.application.ReservationService;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import com.eonedu.domain.reservation.dto.request.ClientReservationUpdateRequest;
import com.eonedu.domain.reservation.dto.response.ClientReservationUpdateResponse;
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
    public ReservationByDateResponse getReservationByDate(@RequestParam(required = false) LocalDate date,
                                                          @RequestParam(required = false) String clientPhone){

        if (date == null && clientPhone == null){
            throw new IllegalArgumentException("date or clientPhone is required");
        }
        else if (date != null && clientPhone != null){
            throw new IllegalArgumentException("date and clientPhone cannot be used together");
        }
        else if (date != null){
            List<Reservation> reservations = reservationService.getReservationByDate(date);
            return new ReservationByDateResponse(reservations);
        }
        else{
            List<Reservation> reservations = reservationService.getReservationByPhone(clientPhone);
            return new ReservationByDateResponse(reservations);
        }
    }

    @PostMapping("/v1/reservations")
    public Long createClientReservation(@RequestBody ClientReservationCreateRequest request){
        ClientReservation reservation = reservationService.createClientReservation(request);

        return reservation.getId();
    }

//    @PutMapping("/v1/reservations/{reservation_id}")
//    public ClientReservationUpdateResponse updateReservation(@PathVariable("reservation_id") Long reservationId,
//                                                      @RequestBody ClientReservationUpdateRequest request){
//        ClientReservation reservation = reservationService.updateReservation(reservationId, request);
//
//        return new ClientReservationUpdateResponse(reservation);
//    }

    @DeleteMapping("/v1/reservations/{reservation_id}")
    public String deleteReservation(@PathVariable("reservation_id") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return "Reservation deleted";
    }
}
