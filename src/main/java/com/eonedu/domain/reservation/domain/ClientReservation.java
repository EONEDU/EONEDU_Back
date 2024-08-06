package com.eonedu.domain.reservation.domain;

import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.domain.CounselType;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class ClientReservation extends Reservation{
    private String clientName;

    private String clientPhone;

    @Builder
    public ClientReservation(Long id, CounselType counselType, Branch branch, LocalDate date, ReservationTime time,  String clientName, String clientPhone) {
        this.id = id;
        this.counselType = counselType;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
    }

    @Deprecated
    public void update(LocalDate date, ReservationTime time, Branch branch, CounselType counselType){
        this.date = date;
        this.time = time;
        this.branch = branch;
        this.counselType = counselType;
    }
}
