package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    @PostMapping("/submitQuiz")
    public String submitQuiz(
            @RequestParam("q1") String q1,
            @RequestParam("q2") String q2,
            @RequestParam("q3") String q3,
            @RequestParam("q4") String q4,
            @RequestParam("q5") String q5,
            @RequestParam("q6") String q6,
            @RequestParam("q7") String q7,
            @RequestParam("q8") String q8,
            @RequestParam("q9") String q9,
            @RequestParam("q10") String q10,
            Model model) {

        // Count selections
        int countA = 0, countB = 0, countC = 0, countD = 0;
        String[] answers = {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};

        for (String ans : answers) {
            switch(ans.toUpperCase()) {
                case "A" -> countA++;
                case "B" -> countB++;
                case "C" -> countC++;
                case "D" -> countD++;
            }
        }

        // Determine career based on the most selected option
        String career;
        int max = Math.max(Math.max(countA, countB), Math.max(countC, countD));

        if (max == countA) career = "Data Analyst";
        else if (max == countB) career = "Software Developer";
        else if (max == countC) career = "Data Scientist";
        else career = "AI / ML Specialist";

        // Pass data to result page
        model.addAttribute("career", career);
        model.addAttribute("countA", countA);
        model.addAttribute("countB", countB);
        model.addAttribute("countC", countC);
        model.addAttribute("countD", countD);

        return "result"; // templates/result.html
    }
}

