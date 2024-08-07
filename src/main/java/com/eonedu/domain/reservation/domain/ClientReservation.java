package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.global.util.RandomIdMaker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class ClientReservation extends Reservation{
    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhone;

    @Column(unique = true, nullable = false)
    private String reservationRandomId;

    @Column(nullable = false)
    private ReservationState state;

    //JPA용 기본 생성자
    protected ClientReservation() {}

    // 매게변수가 많아 Builder를 사용하여 객체를 생성
    @Builder
    private ClientReservation(Long id, CounselType counselType, Branch branch, LocalDate date, ReservationTime time,  String clientName, String clientPhone) {
        this.id = id;
        this.counselType = counselType;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.state = ReservationState.WAITING; // 기본 상태는 대기로 설정
        this.reservationRandomId = RandomIdMaker.makeTwelveRandomId(); // 예약 고유번호 생성
    }
}
