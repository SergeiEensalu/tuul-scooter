package com.tuul.domain.model.vehicle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
    private String id;
    private String model;
    private String location;

    public static Vehicle from(String id, VehicleRow row) {
        return Vehicle.builder()
                .id(id)
                .model(row.getModel())
                .location(row.getLocation())
                .build();
    }
}