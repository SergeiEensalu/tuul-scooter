package com.tuul.application.vehicle;

import com.tuul.application.vehicle.dto.CreateVehicleCommand;
import com.tuul.domain.model.vehicle.Vehicle;
import com.tuul.domain.model.vehicle.VehicleRow;
import com.tuul.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(CreateVehicleCommand command) {
        VehicleRow row = new VehicleRow(command.model(), command.location());
        return vehicleRepository.save(row);
    }
}