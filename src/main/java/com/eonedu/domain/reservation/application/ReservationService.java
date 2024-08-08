package com.eonedu.domain.reservation.application;

import com.eonedu.domain.branch.dao.BranchRepository;
import com.eonedu.domain.branch.domain.Branch;
import com.eonedu.domain.counseltype.dao.CounselTypeRepository;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.reservation.dao.AdminReservationRepository;
import com.eonedu.domain.reservation.dao.ClientReservationRepository;
import com.eonedu.domain.reservation.dao.ReservationRepository;
import com.eonedu.domain.reservation.domain.ClientReservation;
import com.eonedu.domain.reservation.domain.Reservation;
import com.eonedu.domain.reservation.domain.ReservationTime;
import com.eonedu.domain.reservation.dto.request.ClientReservationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AdminReservationRepository adminReservationRepository;
    private final ClientReservationRepository clientReservationRepository;

    private final BranchRepository branchRepository;
    private final CounselTypeRepository counselTypeRepository;

    // 이미 존재하는 상담 예약 조회 (날짜, 지점, 상담유형)
    @Transactional(readOnly = true)
    public List<Reservation> findExistedReservation(Long branchId, Long counselTypeId, LocalDate date){
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        CounselType counselType = counselTypeRepository.findById(counselTypeId)
                .orElseThrow(() -> new IllegalArgumentException("CounselType not found"));

        List<Reservation> reservations = reservationRepository.findByBranchAndCounselTypeAndDate(branch, counselType, date);

        return reservations;
    }

    // 상담 예약 생성
    @Transactional
    public ClientReservation createClientReservation(ClientReservationCreateRequest request){
        Branch branch = branchRepository.findById(request.branchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        CounselType counselType = counselTypeRepository.findById(request.counselTypeId())
                .orElseThrow(() -> new IllegalArgumentException("CounselType not found"));

        ReservationTime reservationTime = ReservationTime.find(request.time());

        validatePossibleReservation(counselType, branch, request.date(), reservationTime);

        ClientReservation clientReservation = ClientReservation.builder()
                .branch(branch)
                .counselType(counselType)
                .date(request.date())
                .time(ReservationTime.find(request.time()))
                .clientName(request.clientName())
                .clientPhone(request.clientPhone())
                .build();

        return clientReservationRepository.save(clientReservation);
    }

    // uuid, 고객 이름, 전화번호 이용해 상담 예약 조회
    @Transactional(readOnly = true)
    public ClientReservation findClientReservation(String reservationUuid, String clientName, String clientPhone){
        ClientReservation reservation =  clientReservationRepository.findByReservationRandomId(reservationUuid)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        validateClient(reservation, clientName, clientPhone);

        //Lazy Loading
        reservation.getBranch().getName();
        reservation.getCounselType().getName();

        return reservation;
    }

    // uuid, 고객 이름, 전화번호 받아 상담 예약 삭제
    @Transactional
    public void cancelClientReservation(String reservationUuid, String clientName, String clientPhone){
        ClientReservation clientReservation = clientReservationRepository.findByReservationRandomId(reservationUuid)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        validateClient(clientReservation, clientName, clientPhone);

        clientReservationRepository.delete(clientReservation);
    }

    // 특정 지점, 상담 유형, 날짜, 시간에 예약이 가능한지 확인
    private void validatePossibleReservation(CounselType counselType, Branch branch, LocalDate date, ReservationTime time){
        // Check if the reservation already exists
        reservationRepository.findByBranchAndCounselTypeAndDate(branch, counselType, date)
                .forEach((reservation) -> {
                    if (reservation.getTime().equals(time)){
                        throw new IllegalArgumentException("Reservation already exists");
                    }
                });
    }

    // 예약한 고객 정보가 일치하는지 확인
    private void validateClient(ClientReservation reservation, String clientName, String clientPhone){
        if (!reservation.getClientName().equals(clientName) || !reservation.getClientPhone().equals(clientPhone)){
            throw new IllegalArgumentException("Client information does not match");
        }
    }
}
