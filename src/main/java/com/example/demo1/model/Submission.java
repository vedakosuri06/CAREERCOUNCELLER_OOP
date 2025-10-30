package com.example.demo1.model;

import java.util.Map;

public class Submission {
    private String name;
    private String email;
    private String rollNo;
    private Map<String, String> answers;
    private Map<String, Integer> scores;
    private String recommendedCareer;

    public Submission(String name, String email, String rollNo, Map<String, String> answers,
                      Map<String, Integer> scores, String recommendedCareer) {
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.answers = answers;
        this.scores = scores;
        this.recommendedCareer = recommendedCareer;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRollNo() { return rollNo; }
    public Map<String, String> getAnswers() { return answers; }
    public Map<String, Integer> getScores() { return scores; }
    public String getRecommendedCareer() { return recommendedCareer; }
}
