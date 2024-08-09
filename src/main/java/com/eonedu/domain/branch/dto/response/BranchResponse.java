package com.eonedu.domain.branch.dto.response;

import com.eonedu.domain.branch.domain.Branch;

public record BranchResponse(Long id, String name) {

        public static BranchResponse from(Branch branch) {
            return new BranchResponse(branch.getId(), branch.getName());
        }
}
