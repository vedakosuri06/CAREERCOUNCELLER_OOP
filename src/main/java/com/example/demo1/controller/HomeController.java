package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // renders home.html
    }

    @GetMapping("/beginassign")
    public String beginAssign() {
        return "beginassign"; // renders beginassign.html
    }
    

    @GetMapping("/q1")
    public String question1() {
        return "q1"; // renders q1.html
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz"; // renders quiz.html
    
    }

    
    



    @GetMapping("/starttest")
    public String starttest() {
        return "starttest";
    }
    

}


