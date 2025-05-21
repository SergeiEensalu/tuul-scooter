package com.tuul.domain.model.reservation;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationRow {
    private String userId;
    private String vehicleId;

    private Date startTime;
    private Date endTime;

    private double startLat;
    private double startLng;
    private double endLat;
    private double endLng;

    private double cost;

    @Builder.Default
    private Date createdAt = new Date();

    @Builder.Default
    private Date updatedAt = new Date();
}