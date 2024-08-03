package com.eonedu.domain.branch.domain;

import com.eonedu.domain.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Branch extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int id;

    @Column(nullable = false)
    private String name;
}
