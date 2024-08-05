package com.eonedu.domain.branch.api;

import com.eonedu.domain.branch.application.BranchService;
import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.branch.dto.request.BranchCreateRequest;
import com.eonedu.domain.branch.dto.response.BranchFindAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/v1/branches")
    public BranchFindAllResponse findAll() {

        return new BranchFindAllResponse(branchService.findAll());
    }

    @PostMapping("/admin/v1/branches")
    public Long createBranch(@RequestBody BranchCreateRequest request) {
        Branch branch = branchService.createBranch(request);

        return branch.getId();
    }
}
