package com.eonedu.domain.reservation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequestClientInformation {
    private String clientName;
    private String clientPhone;
}
