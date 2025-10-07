package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/quiz")
    public String quizPage() {
        return "quiz"; // maps to quiz.html
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // maps to home.html
    }

    @GetMapping("/starttest")
    public String starttest() {
        return "starttest"; // maps to starttest.html
    }
}
