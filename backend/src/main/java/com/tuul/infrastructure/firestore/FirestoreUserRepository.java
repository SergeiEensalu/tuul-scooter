package com.tuul.infrastructure.firestore;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.tuul.domain.model.User;
import com.tuul.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FirestoreUserRepository implements UserRepository {

    private final Firestore firestore;

    public FirestoreUserRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(User user) {
        firestore.collection("users").document(user.getId()).set(user);
    }

    @Override
    public User findByEmail(String email) {
        try {
            QuerySnapshot snapshot = firestore.collection("users")
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
