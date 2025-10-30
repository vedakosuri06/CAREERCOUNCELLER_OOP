package com.example.demo1.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FirebaseConfig {

	private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);
	private static final String DEFAULT_FILENAME = "career-9d7d6-firebase-adminsdk-fbsvc-137f3e2b55.json";

	@PostConstruct
	public void init() {
		try {
			if (!FirebaseApp.getApps().isEmpty()) {
				logger.debug("Firebase already initialized, skipping.");
				return;
			}

			InputStream serviceAccount = null;

			// 1) Try environment variable GOOGLE_APPLICATION_CREDENTIALS
			String envPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
			if (envPath != null && !envPath.isEmpty()) {
				Path p = Paths.get(envPath);
				if (Files.exists(p)) {
					serviceAccount = new FileInputStream(p.toFile());
					logger.info("Loaded Firebase credentials from GOOGLE_APPLICATION_CREDENTIALS: {}", envPath);
				} else {
					logger.warn("GOOGLE_APPLICATION_CREDENTIALS is set but file not found: {}", envPath);
				}
			}

			// 2) Try classpath (src/main/resources)
			if (serviceAccount == null) {
				ClassPathResource resource = new ClassPathResource(DEFAULT_FILENAME);
				if (resource.exists()) {
					serviceAccount = resource.getInputStream();
					logger.info("Loaded Firebase credentials from classpath resource: {}", DEFAULT_FILENAME);
				}
			}

			// 3) Try working directory (useful during local dev)
			if (serviceAccount == null) {
				Path local = Paths.get(DEFAULT_FILENAME);
				if (Files.exists(local)) {
					serviceAccount = new FileInputStream(local.toFile());
					logger.info("Loaded Firebase credentials from working directory: {}", local.toAbsolutePath());
				}
			}

			if (serviceAccount == null) {
				logger.warn("Firebase service account not found; skipping Firebase initialization.");
				return;
			}

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			FirebaseApp.initializeApp(options);
			logger.info("Firebase initialized successfully.");
		} catch (Exception e) {
			logger.error("Failed to initialize Firebase", e);
		}
	}
}

