package com.tuul.domain.model.reservation;

import com.google.cloud.firestore.annotation.Exclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Reservation {

    @Exclude
    private String id;
    private String userId;
    private String vehicleId;

    private Date startTime;
    private Date endTime;

    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;

    private double cost;
}
