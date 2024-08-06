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

    @Transactional(readOnly = true)
    public List<Reservation> findExistedReservation(Long branchId, Long counselTypeId, LocalDate date){
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        CounselType counselType = counselTypeRepository.findById(counselTypeId)
                .orElseThrow(() -> new IllegalArgumentException("CounselType not found"));

        List<Reservation> reservations = reservationRepository.findByBranchAndCounselTypeAndDate(branch, counselType, date);

        return reservations;
    }

    @Transactional
    public ClientReservation createClientReservation(ClientReservationCreateRequest request){
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        CounselType counselType = counselTypeRepository.findById(request.getCounselTypeId())
                .orElseThrow(() -> new IllegalArgumentException("CounselType not found"));

        ReservationTime reservationTime = ReservationTime.find(request.getTime());

        isReservationPossible(counselType, branch, request.getDate(), reservationTime);

        ClientReservation clientReservation = ClientReservation.builder()
                .branch(branch)
                .counselType(counselType)
                .date(request.getDate())
                .time(ReservationTime.find(request.getTime()))
                .clientName(request.getClientName())
                .clientPhone(request.getClientPhone())
                .build();

        return clientReservationRepository.save(clientReservation);
    }

//    @Deprecated
//    @Transactional
//    public ClientReservation updateReservation(Long reservationId, ClientReservationUpdateRequest request) {
//        ClientReservation clientReservation = clientReservationRepository.findById(reservationId)
//                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
//
//        Branch branch = branchRepository.findById(request.getBranchId())
//                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));
//
//        CounselType counselType = counselTypeRepository.findById(request.getCounselTypeId())
//                .orElseThrow(() -> new IllegalArgumentException("CounselType not found"));
//
//        clientReservation.update(request.getDate(), request.getTime(), branch, counselType);
//
//        return clientReservation;
//    }

    @Transactional
    public void cancelClientReservation(String reservationUuid){
        ClientReservation clientReservation = clientReservationRepository.findByReservationRandomId(reservationUuid)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        clientReservationRepository.delete(clientReservation);
    }

    private void isReservationPossible(CounselType counselType, Branch branch, LocalDate date, ReservationTime time){
        // Check if the reservation already exists
        reservationRepository.findByBranchAndCounselTypeAndDate(branch, counselType, date)
                .forEach((reservation) -> {
                    if (reservation.getTime().equals(time)){
                        throw new IllegalArgumentException("Reservation already exists");
                    }
                });
    }
}
