package com.tuul.infrastructure.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.tuul.domain.model.user.User;
import com.tuul.domain.model.user.UserRow;
import com.tuul.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

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
    public User findByEmail(String email) {
        try {
            QuerySnapshot snapshot = firestore.collection(FirestoreCollections.USERS)
                    .whereEqualTo("email", email)
                    .get()
                    .get();

            if (snapshot.isEmpty()) return null;
            return snapshot.getDocuments().get(0).toObject(User.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to query Firestore", e);
        }
    }
}
