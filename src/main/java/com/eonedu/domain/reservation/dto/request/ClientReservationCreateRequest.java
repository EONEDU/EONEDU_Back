package com.eonedu.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientReservationCreateRequest (
        @NotNull Long branchId,
        @NotNull Long counselTypeId,
        @NotNull LocalDate date,
        @NotBlank String time,
        @NotBlank String clientName,
        @NotBlank String clientPhone
) {}
