package com.tuul.repository;


import com.tuul.domain.model.reservation.Reservation;
import com.tuul.domain.model.reservation.ReservationRow;

public interface ReservationRepository {
    Reservation save(ReservationRow reservationRow);
}