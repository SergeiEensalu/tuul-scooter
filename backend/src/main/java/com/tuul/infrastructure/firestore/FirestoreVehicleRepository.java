package com.tuul.infrastructure.firestore;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.tuul.domain.model.vehicle.Vehicle;
import com.tuul.domain.model.vehicle.VehicleRow;
import com.tuul.repository.VehicleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FirestoreVehicleRepository implements VehicleRepository {

    private final Firestore firestore;

    public FirestoreVehicleRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Vehicle save(VehicleRow row) {
        try {
            DocumentReference ref = firestore.collection(FirestoreCollections.VEHICLES)
                    .add(row)
                    .get();

            return Vehicle.from(ref.getId(), row);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save vehicle", e);
        }
    }
}