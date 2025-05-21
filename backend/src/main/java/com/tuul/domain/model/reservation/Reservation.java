package com.tuul.domain.model.reservation;

import com.google.cloud.firestore.annotation.Exclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Reservation {
    private String id;

    private String userId;
    private String vehicleId;

    private Date startTime;
    private Date endTime;

    private double startLat;
    private double startLng;
    private double endLat;
    private double endLng;

    private double cost;

    public static Reservation from(String id, ReservationRow row) {
        return Reservation.builder()
                .id(id)
                .userId(row.getUserId())
                .vehicleId(row.getVehicleId())
                .startTime(row.getStartTime())
                .endTime(row.getEndTime())
                .startLat(row.getStartLat())
                .startLng(row.getStartLng())
                .endLat(row.getEndLat())
                .endLng(row.getEndLng())
                .cost(row.getCost())
                .build();
    }
}
