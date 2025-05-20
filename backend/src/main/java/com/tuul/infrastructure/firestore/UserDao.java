package com.tuul.infrastructure.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.tuul.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserDao {

    private static final String COLLECTION = "users";

    public User findByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                .whereEqualTo("email", email)
                .get();

        QuerySnapshot snapshot = future.get();
        if (snapshot.isEmpty()) return null;

        DocumentSnapshot doc = snapshot.getDocuments().get(0);
        return doc.toObject(User.class);
    }

    public void save(User user) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION).add(user);
    }
}
