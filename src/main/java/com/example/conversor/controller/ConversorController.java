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
        return Integer.toBinaryString(number);
    }

    @GetMapping("/convertToInteger/{binary}")
    public int convertToInteger(@PathVariable String binary) {
        return Integer.parseInt(binary, 2);
    }

    @GetMapping("/convertToRoman/{number}") 
    public String convertToRoman(@PathVariable int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number out of range for Roman numeral conversion");  
        }
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};  
        StringBuilder roman = new StringBuilder();
        roman.append(thousands[number / 1000]);
        roman.append(hundreds[(number % 1000) / 100]);
        roman.append(tens[(number % 100) / 10]);
        roman.append(units[number % 10]);
        System.out.println("Converted number: " + number + " to Roman numeral: " + roman);
        return roman.toString();    
    }     
}