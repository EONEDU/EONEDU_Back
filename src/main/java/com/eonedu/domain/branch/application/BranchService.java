package com.eonedu.domain.branch.application;

import com.eonedu.domain.branch.dao.BranchRepository;
import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.branch.dto.request.BranchCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    @Transactional
    public Branch createBranch(BranchCreateRequest request) {
        Branch branch = Branch.from(request.getName());

        return branchRepository.save(branch);
    }

    @Transactional(readOnly = true)
    public List<Branch> findAll(){
        return branchRepository.findAll();
    }
}
