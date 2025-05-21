package com.tuul.api.vehicle.dto;

import java.util.Date;

public record GetVehicleResponse(
        String id,
        String model,
        String location,
        Date createdAt,
        Date updatedAt
) {
}