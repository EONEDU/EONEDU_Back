package com.eonedu.domain.branch.dto.response;

import com.eonedu.domain.branch.domain.Branch;

import java.util.List;

public record BranchFindAllResponse (List<BranchResponse> branches){

    public static BranchFindAllResponse from(List<Branch> branches) {
        return new BranchFindAllResponse(branches.stream()
                .map(BranchResponse::from)
                .toList());
    }
}
