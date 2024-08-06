package com.eonedu.domain.reservation.dto.request;

import com.eonedu.domain.reservation.domain.ReservationTime;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClientReservationCreateRequest {
    private Long branchId;
    private Long counselTypeId;
    private LocalDate date;
    private String time;
    private String clientName;
    private String clientPhone;
}
