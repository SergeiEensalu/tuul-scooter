package com.tuul.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Reservation {
    private String id;
    private String userId;
    private String vehicleId;

    private Instant startTime;
    private Instant endTime;

    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;

    private double cost;
}
