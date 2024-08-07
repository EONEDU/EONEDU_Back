package com.eonedu.domain.reservation.domain;

// 예약 상태 설정을 위한 Enum
// 예약 대기, 예약 완료, 예약 취소 상태 세 가지로 구성
public enum ReservationState {
    WAITING, CONFIRMED, CANCELED
}
