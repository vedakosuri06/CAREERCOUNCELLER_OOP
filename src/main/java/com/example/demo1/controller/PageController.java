package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about"; // renders about.html
    }

    @GetMapping("/result")
    public String result() {
        return "result"; // renders result.html
    }
}
