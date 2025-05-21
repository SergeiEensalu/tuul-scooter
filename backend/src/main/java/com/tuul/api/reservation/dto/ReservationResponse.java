package com.tuul.api.reservation.dto;

import java.util.Date;

public record ReservationResponse(
        String id,
        String userId,
        String vehicleId,
        Date startTime,
        Date endTime,
        Double startLat,
        Double startLng,
        Double endLat,
        Double endLng,
        double cost
) {
    public static ReservationResponse from(com.tuul.domain.model.Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getVehicleId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStartLat(),
                reservation.getStartLng(),
                reservation.getEndLat(),
                reservation.getEndLng(),
                reservation.getCost()
        );
    }
}
