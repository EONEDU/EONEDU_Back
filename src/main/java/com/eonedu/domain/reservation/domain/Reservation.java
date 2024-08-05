package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.model.BaseEntity;

import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reservation extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    protected Long id;

    @Column(nullable = false)
    protected LocalDate date;

    @Column(nullable = false)
    protected LocalTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    protected Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_type_id")
    protected CounselType counselType;
}
