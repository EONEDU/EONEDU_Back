package com.eonedu.domain.branch.dao;

import com.eonedu.domain.branch.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
