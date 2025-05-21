package com.tuul.api.reservation;

import com.tuul.api.common.dto.ApiResponse;
import com.tuul.api.reservation.dto.ReservationRequest;
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

@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtProvider jwtProvider;

    public ReservationController(ReservationService reservationService, JwtProvider jwtProvider) {
        this.reservationService = reservationService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> reserve(@Valid @RequestBody ReservationRequest request,
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

        reservationService.createReservation(command);
        return ResponseEntity.ok(ApiResponse.success("Reservation created"));
    }
}