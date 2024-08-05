package com.eonedu.domain.reservation.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ClientReservationCreateRequest {
    private Long branchId;
    private Long counselTypeId;
    private LocalDate date;
    private LocalTime time;
    private String clientName;
    private String clientPhone;
}
