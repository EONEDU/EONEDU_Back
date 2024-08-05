package com.eonedu.domain.branch.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BranchCreateRequest {
    @NotBlank(message = "이름은 비워둘 수 없습니다")
    private String name;
}
