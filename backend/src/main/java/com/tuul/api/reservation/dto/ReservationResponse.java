package com.tuul.api.reservation.dto;

import java.util.Date;

public record ReservationResponse(String id, String userId, String vehicleId, Date startTime, Date endTime) {
}