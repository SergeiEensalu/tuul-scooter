package com.tuul.application.reservation;

import com.tuul.application.reservation.dto.CreateReservationCommand;
import com.tuul.domain.model.Reservation;
import com.tuul.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(CreateReservationCommand command) {
        long durationMillis = Duration.between(command.startTime(), command.endTime()).toMillis();
        long durationMinutes = (long) Math.ceil(durationMillis / 60000.0);

        double cost = 1.0 + Math.min(durationMinutes, 10) * 0.5 + Math.max(0, durationMinutes - 10) * 0.3;

        Reservation reservation = Reservation.builder()
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

        return reservationRepository.save(reservation);
    }
}
