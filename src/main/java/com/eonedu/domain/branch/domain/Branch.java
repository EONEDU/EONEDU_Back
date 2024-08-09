package com.eonedu.domain.branch.domain;

import com.eonedu.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    public static Branch from(String name) {
        return new Branch(name);
    }

    // 빌더 패턴용 private 생성자
    private Branch(String name) {
        this.name = name;
    }
}
