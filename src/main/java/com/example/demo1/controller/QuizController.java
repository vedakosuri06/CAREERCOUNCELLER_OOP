package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    @PostMapping("/submitQuiz")
    public String submitQuiz(
            @RequestParam(value = "q1", required = false) String q1,
            @RequestParam(value = "q2", required = false) String q2,
            @RequestParam(value = "q3", required = false) String q3,
            @RequestParam(value = "q4", required = false) String q4,
            @RequestParam(value = "q5", required = false) String q5,
            @RequestParam(value = "q6", required = false) String q6,
            @RequestParam(value = "q7", required = false) String q7,
            @RequestParam(value = "q8", required = false) String q8,
            @RequestParam(value = "q9", required = false) String q9,
            @RequestParam(value = "q10", required = false) String q10,
            Model model) {

        int countA = 0, countB = 0, countC = 0, countD = 0, unanswered = 0;
        String[] answers = {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};

        for (String ans : answers) {
            if (ans == null || ans.trim().isEmpty()) {
                unanswered++;
                continue;
            }
            switch (ans.trim().toUpperCase()) {
                case "A" -> countA++;
                case "B" -> countB++;
                case "C" -> countC++;
                case "D" -> countD++;
                default -> { /* ignore unexpected values */ }
            }
        }

        int totalAnswered = countA + countB + countC + countD;
        int totalQuestions = answers.length;

        // put counts into model for Thymeleaf
        model.addAttribute("countA", countA);
        model.addAttribute("countB", countB);
        model.addAttribute("countC", countC);
        model.addAttribute("countD", countD);
        model.addAttribute("unanswered", unanswered);
        model.addAttribute("totalAnswered", totalAnswered);
        model.addAttribute("totalQuestions", totalQuestions);

        // optional debug log to terminal to confirm controller ran
        System.out.println("Counts => A:" + countA + " B:" + countB + " C:" + countC + " D:" + countD + " unanswered:" + unanswered);

        // decide a simple label (optional)
        String career;
        int max = Math.max(Math.max(countA, countB), Math.max(countC, countD));
        if (max == countA) career = "Data Analyst";
        else if (max == countB) career = "Software Developer";
        else if (max == countC) career = "Data Scientist";
        else if (max == countD) career = "AI / ML Specialist";
        else career = "No clear match";

        model.addAttribute("career", career);

        return "result";
    }
}

