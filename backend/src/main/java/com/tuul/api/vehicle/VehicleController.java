package com.tuul.api.vehicle;

import com.tuul.api.common.dto.ApiResponse;
import com.tuul.api.vehicle.dto.CreateVehicleRequest;
import com.tuul.api.vehicle.dto.CreateVehicleResponse;
import com.tuul.api.vehicle.dto.GetVehicleResponse;
import com.tuul.application.vehicle.VehicleService;
import com.tuul.application.vehicle.dto.CreateVehicleCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateVehicleResponse>> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        CreateVehicleCommand command = new CreateVehicleCommand(request.model(), request.location());
        var created = vehicleService.create(command);

        return ResponseEntity.ok(
                ApiResponse.success("Vehicle created", new CreateVehicleResponse(created.getId(), created.getModel(), created.getLocation()))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetVehicleResponse>>> getAllVehicles() {
        List<GetVehicleResponse> vehicles = vehicleService.findAll().stream()
                .map(vehicle -> new GetVehicleResponse(
                        vehicle.getId(),
                        vehicle.getModel(),
                        vehicle.getLocation(),
                        vehicle.getCreatedAt(),
                        vehicle.getUpdatedAt()
                ))
                .toList();

        return ResponseEntity.ok(ApiResponse.success("Vehicle list", vehicles));
    }
}
