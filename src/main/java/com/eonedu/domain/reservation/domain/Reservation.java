package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    protected Branch branch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "counsel_type_id")
    protected CounselType counselType;
}
