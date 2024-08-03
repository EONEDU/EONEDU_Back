package com.eonedu.domain.branch.domain;

import com.eonedu.domain.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Branch extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int id;

    @Column(nullable = false)
    private String name;
}
