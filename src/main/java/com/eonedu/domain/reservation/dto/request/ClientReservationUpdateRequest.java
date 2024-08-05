package com.eonedu.domain.reservation.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ClientReservationUpdateRequest {
    private LocalDate date;
    private LocalTime time;
    private Long branchId;
    private Long counselTypeId;
}
