package com.tuul.infrastructure.firestore;

import com.google.cloud.firestore.Firestore;
import com.tuul.domain.model.Reservation;
import com.tuul.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FirestoreReservationRepository implements ReservationRepository {

    private final Firestore firestore;

    public FirestoreReservationRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(Reservation reservation) {
        String id = reservation.getId() != null ? reservation.getId() : UUID.randomUUID().toString();
        firestore.collection("reservations").document(id).set(reservation);
    }
}
