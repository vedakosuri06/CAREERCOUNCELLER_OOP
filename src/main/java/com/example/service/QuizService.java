package com.example.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// Legacy service preserved; active service lives in com.example.demo1.service.QuizService
@Service
class QuizServiceLegacyDeprecated {

    public SubmissionLegacyDeprecated evaluateAndStore(String name, String email, String rollNo, Map<String, String> answers) {
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
    SubmissionLegacyDeprecated submission = new SubmissionLegacyDeprecated(name, email, rollNo, answers, scores, recommendedCareer);

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

/**
 * Minimal local implementation so the legacy submission type resolves during compilation.
 * If you have a real model class elsewhere, remove this stub and restore the correct import.
 */
class SubmissionLegacyDeprecated {
    private final String name;
    private final String email;
    private final String rollNo;
    private final Map<String, String> answers;
    private final Map<String, Integer> scores;
    private final String recommendedCareer;

    public SubmissionLegacyDeprecated(String name, String email, String rollNo, Map<String, String> answers, Map<String, Integer> scores, String recommendedCareer) {
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.answers = answers;
        this.scores = scores;
        this.recommendedCareer = recommendedCareer;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public String getRecommendedCareer() {
        return recommendedCareer;
    }
}
