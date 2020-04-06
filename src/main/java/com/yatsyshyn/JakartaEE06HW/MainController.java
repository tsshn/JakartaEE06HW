package com.yatsyshyn.JakartaEE06HW;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}