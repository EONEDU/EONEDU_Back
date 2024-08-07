package com.eonedu.domain.reservation.domain;

// 예약 가능 시간을 설정하기 위한 Enum
public enum ReservationTime {

    // 30분 단위로 예약 가능 10:00 ~ 21:30
    TEN("10:00"), TEN_HALF("10:30"),
    ELEVEN("11:00"), ELEVEN_HALF("11:30"),
    TWELVE("12:00"), TWELVE_HALF("12:30"),
    THIRTEEN("13:00"), THIRTEEN_HALF("13:30"),
    FOURTEEN("14:00"), FOURTEEN_HALF("14:30"),
    FIFTEEN("15:00"), FIFTEEN_HALF("15:30"),
    SIXTEEN("16:00"), SIXTEEN_HALF("16:30"),
    SEVENTEEN("17:00"), SEVENTEEN_HALF("17:30"),
    EIGHTEEN("18:00"), EIGHTEEN_HALF("18:30"),
    NINETEEN("19:00"), NINETEEN_HALF("19:30"),
    TWENTY("20:00"), TWENTY_HALF("20:30"),
    TWENTY_ONE("21:00"), TWENTY_ONE_HALF("21:30");
    private final String timeString;

    ReservationTime(String timeString) {
        this.timeString = timeString;
    }

    public String getTimeString() {
        return timeString;
    }

    // 시간 문자열 ex) "10:00"을 받아서 해당하는 Enum을 반환, 없으면 에러 반환
    public static ReservationTime find(String time){
        for(ReservationTime reservationTime : ReservationTime.values()){
            if(reservationTime.getTimeString().equals(time)){
                return reservationTime;
            }
        }

        throw new IllegalArgumentException("예약 가능 시간이 아닙니다.");
    }
}
