package com.eonedu.domain.counseltype.api;

import com.eonedu.domain.counseltype.application.CounselTypeService;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.counseltype.dto.request.CounselTypeCreateRequest;
import com.eonedu.domain.counseltype.dto.response.CounselTypeFindAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CounselTypeController {
    private final CounselTypeService counselTypeService;

    @GetMapping("/v1/counsel-types")
    public CounselTypeFindAllResponse findAll() {
        return CounselTypeFindAllResponse.from(counselTypeService.findAll());
    }

    @PostMapping("/admin/v1/counsel-types")
    public Long createCounselType(@Valid @RequestBody CounselTypeCreateRequest request) {
        CounselType counselType = counselTypeService.createCounselType(request);

        return counselType.getId();
    }
}
