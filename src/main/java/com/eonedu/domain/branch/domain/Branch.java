package com.eonedu.domain.branch.domain;

import com.eonedu.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Branch extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    public static Branch from(String name) {
        Branch branch = new Branch();
        branch.name = name;
        return branch;
    }
}
