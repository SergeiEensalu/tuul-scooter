package com.tuul.repository;


import com.tuul.domain.model.reservation.Reservation;
import com.tuul.domain.model.reservation.ReservationRow;

import java.util.Date;

public interface ReservationRepository {
    Reservation save(ReservationRow reservationRow);

    boolean isVehicleReservedBetween(String vehicleId, Date newStart, Date newEnd);
}