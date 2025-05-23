package com.tuul.infrastructure.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.tuul.domain.model.user.User;
import com.tuul.domain.model.user.UserRow;
import com.tuul.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FirestoreUserRepository implements UserRepository {

    private final Firestore firestore;

    public FirestoreUserRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public User save(UserRow userRow) {
        try {
            ApiFuture<DocumentReference> future = firestore.collection(FirestoreCollections.USERS).add(userRow);
            DocumentReference ref = future.get();

            return User.from(ref.getId(), userRow);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            QuerySnapshot snapshot = firestore.collection(FirestoreCollections.USERS)
                    .whereEqualTo("email", email)
                    .get()
                    .get();

            if (snapshot.isEmpty()) {
                return Optional.empty();
            }

            DocumentSnapshot document = snapshot.getDocuments().get(0);

            UserRow row = document.toObject(UserRow.class);
            return Optional.of(User.from(document.getId(), row));
        } catch (Exception e) {
            throw new RuntimeException("Failed to query Firestore", e);
        }
    }
}
