package com.example.conversor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/conversor")
@RestController
public class ConversorController {

    @GetMapping("/convertToBinary/{number}")
    public String convertToBinary(@PathVariable int number) {
        System.out.println("Converting number: " + number);
        return Integer.toBinaryString(number)
    }

    @GetMapping("/convertToInteger/{binary}")
    public int convertToInteger(@PathVariable String binary) {
        return Integer.parseInt(binary, 2);
    }
}