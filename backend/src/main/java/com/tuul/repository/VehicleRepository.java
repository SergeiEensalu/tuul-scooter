package com.tuul.repository;

import com.tuul.domain.model.vehicle.Vehicle;
import com.tuul.domain.model.vehicle.VehicleRow;

import java.util.List;

public interface VehicleRepository {
    Vehicle save(VehicleRow row);
    boolean existsById(String id);
    List<Vehicle> findAll();
}