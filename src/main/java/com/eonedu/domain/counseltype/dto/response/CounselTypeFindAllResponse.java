package com.eonedu.domain.counseltype.dto.response;

import com.eonedu.domain.counseltype.domain.CounselType;
import lombok.Getter;

import java.util.List;

@Getter
public class CounselTypeFindAllResponse {
    private List<CounselTypeDto> counselTypes;

    public CounselTypeFindAllResponse(List<CounselType> counselTypes) {
        this.counselTypes = counselTypes.stream()
                .map(CounselTypeDto::new)
                .toList();
    }


    @Getter
    static class CounselTypeDto {
        private Long id;
        private String name;

        public CounselTypeDto(CounselType counselType) {
            this.id = counselType.getId();
            this.name = counselType.getName();
        }
    }
}
