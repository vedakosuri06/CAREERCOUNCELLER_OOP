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

        // Count occurrences of each answer (robust: trim, take first letter, uppercase)
        for (String ans : answers.values()) {
            if (ans == null) continue;
            String cleaned = ans.trim();
            if (cleaned.isEmpty()) continue;
            // Use the first character (handles values like "A", "A.", "A - ...", or full labels)
            String key = cleaned.substring(0,1).toUpperCase();
            if (scores.containsKey(key)) {
                scores.put(key, scores.get(key) + 1);
            }
        }

    // Determine the recommended career key and text
    String key = getCareerKeyFromScores(scores);
    String recommendedCareer = careerFor(key);
    String careerDescription = careerDescriptionFor(key);
    String aiRecommendation = aiRecommendationFor(key);

    // Create Submission object (includes descriptions and AI suggestion)
    Submission submission = new Submission(name, email, rollNo, answers, scores, recommendedCareer, careerDescription, aiRecommendation);

        // ✅ OPTIONAL: store submission in Firebase or Database
        // firebaseService.save(submission);

        return submission;
    }

    private String getCareerFromScores(Map<String, Integer> scores) {
        // Determine max score and handle ties deterministically using a fixed priority order
        int max = -1;
        // priority order when scores tie: prefer A, then B, then C, then D
        String[] priority = new String[]{"A","B","C","D"};
        for (String k : priority) {
            Integer v = scores.get(k);
            if (v != null && v > max) {
                max = v;
            }
        }

        // find the first key in priority that has the max value
        String best = "A";
        for (String k : priority) {
            if (scores.getOrDefault(k, 0) == max) {
                best = k;
                break;
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

    // Return the key (A/B/C/D) determined from scores using the current tie-break priority.
    private String getCareerKeyFromScores(Map<String, Integer> scores) {
        int max = -1;
        String[] priority = new String[]{"A","B","C","D"};
        for (String k : priority) {
            Integer v = scores.get(k);
            if (v != null && v > max) {
                max = v;
            }
        }
        String best = "A";
        for (String k : priority) {
            if (scores.getOrDefault(k, 0) == max) {
                best = k;
                break;
            }
        }
        return best;
    }

    private String careerFor(String key) {
        switch (key) {
            case "A": return "Artificial Intelligence / Machine Learning Engineer";
            case "B": return "Data Science / Analytics";
            case "C": return "Backend / Software Development";
            case "D": return "Research / Academia";
            default: return "General Software Engineer";
        }
    }

    private String careerDescriptionFor(String key) {
        switch (key) {
            case "A":
                return "AI/ML engineers build models that learn from data. Typical skills: Python, machine learning, deep learning, statistics, and model deployment.";
            case "B":
                return "Data professionals analyze data to derive insights. Typical skills: SQL, Python/R, statistics, visualization, and business domain knowledge.";
            case "C":
                return "Backend developers design and build server-side systems. Typical skills: Java, Spring, databases, APIs, and system design.";
            case "D":
                return "Research/Academia focuses on theoretical advances and publications. Typical skills: deep subject-matter expertise, research methodology, and scientific communication.";
            default:
                return "A software engineering career with a mix of practical development skills.";
        }
    }

    private String aiRecommendationFor(String key) {
        switch (key) {
            case "A":
                return "Start with a strong Python foundation (https://www.learnpython.org/). Study machine learning basics (Coursera: <a href=\"https://www.coursera.org/learn/machine-learning\" target=\"_blank\" rel=\"noopener\">Andrew Ng - ML</a>) and deep learning (DeepLearning.AI/TensorFlow/PyTorch). Build projects: a classification model, an image recognizer, and deploy a model as an API (FastAPI or Flask). Useful resources: <a href=\"https://www.tensorflow.org/tutorials\" target=\"_blank\" rel=\"noopener\">TensorFlow Tutorials</a>, <a href=\"https://pytorch.org/tutorials/\" target=\"_blank\" rel=\"noopener\">PyTorch Tutorials</a>, <a href=\"https://www.kaggle.com/learn/overview\" target=\"_blank\" rel=\"noopener\">Kaggle Learn</a>.";
            case "B":
                return "Learn SQL fundamentals (<a href=\"https://www.codecademy.com/learn/learn-sql\" target=\"_blank\" rel=\"noopener\">Codecademy - SQL</a>) and statistics (Khan Academy). Practice data analysis and visualization in Python (Pandas, Matplotlib, Seaborn) and try Kaggle micro-courses: <a href=\"https://www.kaggle.com/learn/overview\" target=\"_blank\" rel=\"noopener\">Kaggle Learn</a>. Build a portfolio: exploratory analyses, dashboards (Tableau or Power BI), and small ML models. Recommended: <a href=\"https://www.coursera.org/specializations/jhu-data-science\" target=\"_blank\" rel=\"noopener\">Johns Hopkins Data Science</a>.";
            case "C":
                return "Strengthen core programming (Java, Python, or Node.js). Learn to build REST APIs and web services (Spring Boot for Java). Practice databases, transactions, and deployment. Good starting points: <a href=\"https://spring.io/guides/gs/spring-boot/\" target=\"_blank\" rel=\"noopener\">Spring Getting Started</a>, <a href=\"https://www.freecodecamp.org/news/rest-api-design-best-practices/\" target=\"_blank\" rel=\"noopener\">REST API patterns</a>, and system design primers: <a href=\"https://github.com/donnemartin/system-design-primer\" target=\"_blank\" rel=\"noopener\">System Design Primer</a>. Build projects with Spring Boot and dockerize them.";
            case "D":
                return "Read recent papers (arXiv) and follow conferences in your area (NeurIPS, ICML, SIGMOD, etc.). Strengthen math and statistics (linear algebra, probability). Useful resources: <a href=\"https://arxiv.org/\" target=\"_blank\" rel=\"noopener\">arXiv</a>, <a href=\"https://ocw.mit.edu/index.htm\" target=\"_blank\" rel=\"noopener\">MIT OpenCourseWare</a>. Start a small research project, document it, and aim to publish or present at workshops. Consider advanced degrees for deep research roles.";
            default:
                return "Explore programming foundations and small projects to discover what you enjoy most. Try free interactive tutorials (freeCodeCamp, Codecademy) and small guided projects to find your interest.";
        }
    }
}

