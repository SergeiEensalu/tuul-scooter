package com.tuul.application.reservation;

import com.tuul.application.reservation.dto.CreateReservationCommand;
import com.tuul.domain.exception.AppException;
import com.tuul.domain.model.reservation.Reservation;
import com.tuul.domain.model.reservation.ReservationRow;
import com.tuul.repository.ReservationRepository;
import com.tuul.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.tuul.domain.exception.ErrorCode;

import java.time.Duration;
import java.util.Date;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;

    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;

    }

    public Reservation createReservation(CreateReservationCommand command) {
        if (!vehicleRepository.existsById(command.vehicleId())) {
            throw new AppException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with ID: " + command.vehicleId());
        }

        long durationMillis = Duration.between(command.startTime(), command.endTime()).toMillis();
        long durationMinutes = (long) Math.ceil(durationMillis / 60000.0);

        // Comment by Sergei Eensalu: probably exist better place for formula definition. But lets left it here.
        double cost = 1.0 + Math.min(durationMinutes, 10) * 0.5 + Math.max(0, durationMinutes - 10) * 0.3;

        ReservationRow reservationRow = ReservationRow.builder()
                .userId(command.userId())
                .vehicleId(command.vehicleId())
                .startTime(Date.from(command.startTime()))
                .endTime(Date.from(command.endTime()))
                .startLat(command.startLat())
                .startLng(command.startLng())
                .endLat(command.endLat())
                .endLng(command.endLng())
                .cost(cost)
                .build();

        return reservationRepository.save(reservationRow);
    }
}
