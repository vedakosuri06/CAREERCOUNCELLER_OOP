package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // renders index.html
    }

    // Note: /home and /beginassign are handled by HomeController to avoid duplicate mappings.


    @GetMapping("/q2")
    public String question2() {
        
        return "q2"; // renders q2.html
    }

    @GetMapping("/q3")
    public String question3() {
        return "q3"; // renders q3.html
    }

    @GetMapping("/q4")
    public String question4() {
        return "q4"; // renders q4.html
    }

    @GetMapping("/q5")
    public String question5() {
        return "q5"; // renders q5.html
    }

    @GetMapping("/q6")
    public String question6() {
        return "q6"; // renders q6.html
    }

    @GetMapping("/q7")
    public String question7() {
        return "q7"; // renders q7.html
    }

    @GetMapping("/q8")
    public String question8() {
        return "q8"; // renders q8.html
    }

    @GetMapping("/q9")
    public String question9() {
        return "q9"; // renders q9.html
    }

    @GetMapping("/q10")
    public String question10() {
        return "q10"; // renders q10.html
    }

    @GetMapping("/q11")
    public String question11() {
        return "q11"; // renders q11.html
    }
    

    @GetMapping("/about")
    public String about() {
        return "about"; // renders about.html
    }

    @GetMapping("/result")
    public String result(org.springframework.ui.Model model) {
        // If the result page is requested directly (no submission in model), redirect home.
        if (model.containsAttribute("submission") || model.containsAttribute("scores")) {
            return "result"; // render only when model was populated by POST /submitQuiz
        }
        return "redirect:/";
    }
    



}
