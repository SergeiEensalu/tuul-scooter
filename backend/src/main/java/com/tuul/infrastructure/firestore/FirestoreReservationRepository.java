package com.tuul.infrastructure.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.tuul.domain.model.reservation.Reservation;
import com.tuul.domain.model.reservation.ReservationRow;
import com.tuul.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreReservationRepository implements ReservationRepository {

    private final Firestore firestore;

    public FirestoreReservationRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Reservation save(ReservationRow reservationRow) {
        try {
            ApiFuture<DocumentReference> future = firestore.collection(FirestoreCollections.RESERVATIONS).add(reservationRow);
            DocumentReference ref = future.get();

            return Reservation.from(ref.getId(), reservationRow);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to save reservation", e);
        }
    }
}
