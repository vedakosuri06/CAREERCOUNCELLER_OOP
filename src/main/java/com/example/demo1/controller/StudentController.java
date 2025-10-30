package com.example.demo1.controller;

import com.example.demo1.model.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student student) {
        if (student == null ||
                student.getName() == null || student.getName().trim().isEmpty() ||
                student.getGmail() == null || student.getGmail().trim().isEmpty() ||
                student.getRollno() == null || student.getRollno().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Missing name, gmail or rollno");
        }

        try {
            Firestore db = FirestoreClient.getFirestore();
            Map<String, Object> doc = new HashMap<>();
            doc.put("name", student.getName());
            doc.put("gmail", student.getGmail());
            doc.put("rollno", student.getRollno());
            ApiFuture<WriteResult> writeFuture = db.collection("students")
                                                   .document(student.getRollno())
                                                   .set(doc);
            WriteResult result = writeFuture.get(10, TimeUnit.SECONDS);
            logger.info("Stored student {} (roll {}) at {}", student.getName(), student.getRollno(), result.getUpdateTime());
            return ResponseEntity.ok("Saved: " + result.getUpdateTime());
        } catch (Exception e) {
            logger.error("Error storing student", e);
            return ResponseEntity.status(500).body("Error storing student: " + e.getMessage());
        }
    }
}
