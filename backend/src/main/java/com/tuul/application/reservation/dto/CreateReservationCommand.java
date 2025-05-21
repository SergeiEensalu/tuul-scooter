package com.tuul.application.reservation.dto;

import java.time.Instant;

public record CreateReservationCommand(
        String userId,
        String vehicleId,
        Instant startTime,
        Instant endTime,
        double startLat,
        double startLng,
        double endLat,
        double endLng
) {}