package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";   // index.html
    }

    @GetMapping("/home")
    public String home() {
        return "home";    // home.html
    }

    @GetMapping("/about")
    public String about() {
        return "about";   // about.html
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";    // quiz.html
    }

    @GetMapping("/q2")
    public String q2() {
        return "q2";      // q2.html
    }

    @GetMapping("/q3")
    public String q3() {
        return "q3";      // q3.html
    }

    @GetMapping("/q4")
    public String q4() {
        return "q4";      // q4.html
    }

    @GetMapping("/q5")
    public String q5() {    
        return "q5";      // q5.html
    }

    @GetMapping("/q6")
    public String q6() {
        return "q6";      // q6.html
    }   

    @GetMapping("/q7")
    public String q7() {    
        return "q7";      // q7.html
    }

    @GetMapping("/q8")
    public String q8() {    
        return "q8";      // q8.html
    }
    @GetMapping("/q9")
    public String q9() {
        return "q9";      // q9.html
    }
    @GetMapping("/q10")
    public String q10() {   
        return "q10";     // q10.html
    }


    @GetMapping("/result")
    public String result() {
        return "result";  // result.html
    }   

}

