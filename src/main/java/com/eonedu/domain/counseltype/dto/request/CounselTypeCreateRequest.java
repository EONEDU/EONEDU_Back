package com.eonedu.domain.counseltype.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CounselTypeCreateRequest (
    @NotBlank(message = "이름은 비워둘 수 없습니다") String name
) {}
