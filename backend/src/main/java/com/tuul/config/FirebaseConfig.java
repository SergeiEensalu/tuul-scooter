package com.tuul.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    private final FirebaseProperties firebaseProperties;

    public FirebaseConfig(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
    }

    @Bean
    public Firestore firestore() {
        try {
            String json = String.format("""
                            {
                              "type": "%s",
                              "project_id": "%s",
                              "private_key_id": "%s",
                              "private_key": "%s",
                              "client_email": "%s",
                              "client_id": "%s",
                              "auth_uri": "%s",
                              "token_uri": "%s",
                              "auth_provider_x509_cert_url": "%s",
                              "client_x509_cert_url": "%s",
                              "universe_domain": "%s"
                            }
                            """,
                    firebaseProperties.getType(),
                    firebaseProperties.getProjectId(),
                    firebaseProperties.getPrivateKeyId(),
                    firebaseProperties.getPrivateKey().replace("\\n", "\n"),
                    firebaseProperties.getClientEmail(),
                    firebaseProperties.getClientId(),
                    firebaseProperties.getAuthUri(),
                    firebaseProperties.getTokenUri(),
                    firebaseProperties.getAuthProviderX509CertUrl(),
                    firebaseProperties.getClientX509CertUrl(),
                    firebaseProperties.getUniverseDomain()
            );

            InputStream serviceAccount = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            return FirestoreClient.getFirestore();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
