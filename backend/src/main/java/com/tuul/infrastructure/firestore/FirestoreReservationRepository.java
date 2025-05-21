package com.tuul.infrastructure.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.tuul.domain.model.reservation.Reservation;
import com.tuul.domain.model.reservation.ReservationRow;
import com.tuul.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Override
    public boolean isVehicleReservedBetween(String vehicleId, Date newStart, Date newEnd) {
        try {
            QuerySnapshot snapshot = firestore.collection(FirestoreCollections.RESERVATIONS)
                    .whereEqualTo("vehicleId", vehicleId)
                    .whereLessThan("startTime", newEnd)
                    .whereGreaterThan("endTime", newStart)
                    .limit(1)
                    .get()
                    .get();

            return !snapshot.isEmpty();
        } catch (Exception e) {
            throw new RuntimeException("Failed to check reservation overlap", e);
        }
    }
}
