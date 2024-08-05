package com.eonedu.domain.branch.dto.response;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import lombok.Getter;

import java.util.List;

@Getter
public class BranchFindAllResponse {
    private List<BranchDto> branches;

    public BranchFindAllResponse(List<Branch> branches) {
        this.branches = branches.stream()
                .map(BranchDto::new)
                .toList();
    }


    @Getter
    static class BranchDto {
        private Long id;
        private String name;

        public BranchDto(Branch branch) {
            this.id = branch.getId();
            this.name = branch.getName();
        }
    }
}
