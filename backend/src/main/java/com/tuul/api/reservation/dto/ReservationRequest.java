package com.tuul.api.reservation.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record ReservationRequest(
        @NotNull String vehicleId,
        @NotNull Instant startTime,
        @NotNull Instant endTime,
        @NotNull Double startLatitude,
        @NotNull Double startLongitude,
        @NotNull Double endLatitude,
        @NotNull Double endLongitude
) {}
