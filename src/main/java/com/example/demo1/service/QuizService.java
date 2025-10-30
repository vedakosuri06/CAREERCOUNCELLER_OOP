package com.example.demo1.service;

import com.example.demo1.model.Submission;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizService {

    public Submission evaluateAndStore(String name, String email, String rollNo, Map<String, String> answers) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("A", 0);
        scores.put("B", 0);
        scores.put("C", 0);
        scores.put("D", 0);

        // Count occurrences of each answer
        for (String ans : answers.values()) {
            if (ans == null) continue;
            ans = ans.toUpperCase();
            if (scores.containsKey(ans)) {
                scores.put(ans, scores.get(ans) + 1);
            }
        }

        // Determine the recommended career
        String recommendedCareer = getCareerFromScores(scores);

        // Create Submission object
        Submission submission = new Submission(name, email, rollNo, answers, scores, recommendedCareer);

        // ✅ OPTIONAL: store submission in Firebase or Database
        // firebaseService.save(submission);

        return submission;
    }

    private String getCareerFromScores(Map<String, Integer> scores) {
        int max = -1;
        String best = "A"; // default

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                best = entry.getKey();
            }
        }

        // Career mapping
        switch (best) {
            case "A":
                return "Artificial Intelligence / Machine Learning Engineer";
            case "B":
                return "Data Science / Analytics";
            case "C":
                return "Backend / Software Development";
            case "D":
                return "Research / Academia";
            default:
                return "General Software Engineer";
        }
    }
}
