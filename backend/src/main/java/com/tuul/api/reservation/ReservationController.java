package com.tuul.api.reservation;

import com.tuul.api.common.dto.ApiResponse;
import com.tuul.api.reservation.dto.ReservationRequest;
import com.tuul.api.reservation.dto.ReservationResponse;
import com.tuul.application.reservation.ReservationService;
import com.tuul.application.reservation.dto.CreateReservationCommand;
import com.tuul.security.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Tag(name = "Reservations", description = "Endpoints for scooter reservations")
@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtProvider jwtProvider;

    public ReservationController(ReservationService reservationService, JwtProvider jwtProvider) {
        this.reservationService = reservationService;
        this.jwtProvider = jwtProvider;
    }

    @Operation(
            summary = "Create a reservation",
            description = "Creates a new scooter reservation for a logged-in user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping
    public ResponseEntity<ApiResponse<ReservationResponse>> reserve(@Valid @RequestBody ReservationRequest request,
                                                                    @RequestHeader("Authorization") String token) {
        String userId = jwtProvider.validateAndExtractUserId(token.replace("Bearer ", ""));
        CreateReservationCommand command = new CreateReservationCommand(
                userId,
                request.vehicleId(),
                request.startTime(),
                request.endTime(),
                request.startLatitude(),
                request.startLongitude(),
                request.endLatitude(),
                request.endLongitude()
        );

        // Comment by SERGEI EENSALU: Var to prevent import Reservation from Domain/Service layer.
        var createdReservation = reservationService.createReservation(command);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Reservation created",
                        new ReservationResponse(
                                createdReservation.getId(),
                                createdReservation.getUserId(),
                                createdReservation.getVehicleId(),
                                createdReservation.getStartTime(),
                                createdReservation.getEndTime()
                        )
                )
        );
    }
}