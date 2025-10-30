package com.example.legacy.model;

import java.util.Map;

/**
 * Minimal legacy submission model used by the deprecated quiz service.
 */
@Deprecated
public class SubmissionLegacyDeprecated {

    private final String name;
    private final String email;
    private final String rollNo;
    private final Map<String, String> answers;
    private final Map<String, Integer> scores;
    private final String recommendedCareer;

    public SubmissionLegacyDeprecated(String name, String email, String rollNo,
                                      Map<String, String> answers,
                                      Map<String, Integer> scores,
                                      String recommendedCareer) {
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

    @Override
    public String toString() {
        return "SubmissionLegacyDeprecated{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", recommendedCareer='" + recommendedCareer + '\'' +
                '}';
    }
}
