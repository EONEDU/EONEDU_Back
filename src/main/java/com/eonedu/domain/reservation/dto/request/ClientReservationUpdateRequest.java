package com.eonedu.domain.reservation.dto.request;

import com.eonedu.domain.reservation.domain.ReservationTime;
import lombok.Getter;

import java.time.LocalDate;

@Deprecated
@Getter
public class ClientReservationUpdateRequest {
    private LocalDate date;
    private ReservationTime time;
    private Long branchId;
    private Long counselTypeId;
}
