package com.tuul.repository;


import com.tuul.domain.model.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
}