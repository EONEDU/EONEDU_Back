package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

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
    @Enumerated(EnumType.STRING)
    protected ReservationTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @Column(nullable = false)
    protected Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_type_id")
    @Column(nullable = false)
    protected CounselType counselType;
}
