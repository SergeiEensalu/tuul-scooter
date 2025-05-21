package com.tuul.repository;


import com.tuul.domain.model.Reservation;

public interface ReservationRepository {
    void save(Reservation reservation);
}