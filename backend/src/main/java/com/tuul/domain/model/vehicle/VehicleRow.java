package com.tuul.domain.model.vehicle;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRow {
    private String model;
    private String location;
}