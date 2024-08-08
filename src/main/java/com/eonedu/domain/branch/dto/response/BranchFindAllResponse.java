package com.eonedu.domain.branch.dto.response;

import com.eonedu.domain.branch.domain.Branch;

import java.util.List;

public record BranchFindAllResponse (List<BranchDto> branches){

    public static BranchFindAllResponse from(List<Branch> branches) {
        return new BranchFindAllResponse(branches.stream()
                .map(BranchDto::from)
                .toList());
    }

    static record BranchDto (Long id, String name) {

        public static BranchDto from(Branch branch) {
            return new BranchDto(branch.getId(), branch.getName());
        }
    }
}
