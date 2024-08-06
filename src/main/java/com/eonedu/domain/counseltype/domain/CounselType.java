package com.eonedu.domain.counseltype.domain;

import com.eonedu.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class CounselType extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counsel_type_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public static CounselType from(String name) {
        CounselType counselType = new CounselType();
        counselType.setName(name);

        return counselType;
    }
}
