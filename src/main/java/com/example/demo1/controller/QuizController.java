package com.example.demo1.controller;

import com.example.demo1.model.Submission;
import com.example.demo1.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/submitQuiz")
    public String submitQuiz(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String rollNo,
        @RequestParam Map<String,String> allParams,
        Model model
    ) {
        try {
            Map<String,String> answers = new HashMap<>();
            for (int i=1;i<=10;i++) {
                String key = "q" + i;
                // Store as Q1..Q10 keys
                answers.put("Q" + i, allParams.getOrDefault(key, ""));
            }

            // Prepare ordered list of answers (Q1..Q10) for client-side consumption
            List<String> answerList = new ArrayList<>();
            for (int i=1;i<=10;i++) {
                answerList.add(answers.getOrDefault("Q" + i, ""));
            }

            // Ensure null-safe values for storage/display
            String safeName = name != null ? name : "";
            String safeEmail = email != null ? email : "";
            String safeRoll = rollNo != null ? rollNo : "";

            Submission submission = quizService.evaluateAndStore(safeName, safeEmail, safeRoll, answers);
            model.addAttribute("submission", submission);
            model.addAttribute("scores", submission.getScores());
            model.addAttribute("recommended", submission.getRecommendedCareer());
            // expose ordered answers as "answers" so Thymeleaf can render them into data-attributes
            model.addAttribute("answers", answerList);
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error processing submission: " + e.getMessage());
            return "error";
        }
    }
}

