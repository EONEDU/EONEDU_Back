package com.eonedu.domain.reservation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RequestClientInformation (@NotEmpty String clientName,
                                        @NotEmpty String clientPhone) { }