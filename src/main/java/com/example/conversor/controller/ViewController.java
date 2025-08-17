package com.example.conversor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/conversor")
    public String conversorPage() {
        return "conversor"; // busca conversor.html en src/main/resources/templates
    }
}
