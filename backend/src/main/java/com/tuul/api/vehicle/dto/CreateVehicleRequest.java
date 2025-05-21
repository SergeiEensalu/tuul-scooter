package com.tuul.api.vehicle.dto;


import jakarta.validation.constraints.NotBlank;

public record CreateVehicleRequest(
        @NotBlank(message = "Model is required")
        String model,

        @NotBlank(message = "Location is required")
        String location
) {}