package com.tuul.repository;

import com.tuul.domain.model.vehicle.Vehicle;
import com.tuul.domain.model.vehicle.VehicleRow;

public interface VehicleRepository {
    Vehicle save(VehicleRow row);
}