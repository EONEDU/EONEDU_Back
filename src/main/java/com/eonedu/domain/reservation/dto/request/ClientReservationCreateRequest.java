package com.eonedu.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientReservationCreateRequest (
        @NotNull(message = "지점은 비워둘 수 없습니다") Long branchId,
        @NotNull(message = "상담 종류는 비워둘 수 없습니다") Long counselTypeId,
        @NotNull(message = "날짜는 비워둘 수 없습니다") LocalDate date,
        @NotBlank(message = "시간은 비워둘 수 없습니다") String time,
        @NotBlank(message = "고객 이름은 비워둘 수 없습니다") String clientName,
        @NotBlank(message = "고객 전화번호는 비워둘 수 없습니다") String clientPhone
) {}
