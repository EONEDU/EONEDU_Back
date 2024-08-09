package com.eonedu.domain.counseltype.dto.response;

import com.eonedu.domain.counseltype.domain.CounselType;

import java.util.List;

public record CounselTypeFindAllResponse (List<CounselTypeResponse> counselTypes) {

    public static CounselTypeFindAllResponse from(List<CounselType> counselTypes) {
        return new CounselTypeFindAllResponse(counselTypes.stream()
                .map(CounselTypeResponse::from)
                .toList());
    }
}
