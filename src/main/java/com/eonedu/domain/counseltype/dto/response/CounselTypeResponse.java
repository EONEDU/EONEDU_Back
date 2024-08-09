package com.eonedu.domain.counseltype.dto.response;

import com.eonedu.domain.counseltype.domain.CounselType;

public record CounselTypeResponse (Long id, String name) {
    public static CounselTypeResponse from(CounselType counselType) {
        return new CounselTypeResponse(counselType.getId(), counselType.getName());
    }
}
