package com.eonedu.domain.counseltype.dto.response;

import com.eonedu.domain.counseltype.domain.CounselType;

import java.util.List;

public record CounselTypeFindAllResponse (List<CounselTypeDto> counselTypes) {

    public static CounselTypeFindAllResponse from(List<CounselType> counselTypes) {
        return new CounselTypeFindAllResponse(counselTypes.stream()
                .map(CounselTypeDto::from)
                .toList());
    }


    static record CounselTypeDto (Long id, String name) {

        public static CounselTypeDto from(CounselType counselType) {
            return new CounselTypeDto(counselType.getId(), counselType.getName());
        }
    }
}
