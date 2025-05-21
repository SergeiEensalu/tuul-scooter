package com.tuul.domain.model.vehicle;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Vehicle {
    private String id;
    private String model;
    private String location;

    private Date createdAt;
    private Date updatedAt;

    public static Vehicle from(String id, VehicleRow row) {
        return Vehicle.builder()
                .id(id)
                .model(row.getModel())
                .location(row.getLocation())
                .createdAt(row.getCreatedAt())
                .updatedAt(row.getUpdatedAt())
                .build();
    }
}