package com.example.conversor;

import com.example.conversor.controller.ConversorController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ConversorControllerTest {

    private final ConversorController conversorController = new ConversorController();

    @Test
    void testConvertToBinary() {
        int number = 10;
        String expectedBinary = "1010";
        String actualBinary = conversorController.convertToBinary(number);
        assertEquals(expectedBinary, actualBinary, "The binary conversion is incorrect");
    }

    @Test
    void testConvertToBinaryWithZero() {
        int number = 0;
        String expectedBinary = "0";
        String actualBinary = conversorController.convertToBinary(number);
        assertEquals(expectedBinary, actualBinary, "The binary conversion for zero is incorrect");
    }

    @Test
    void testConvertToInteger() {
        String binary = "1010";
        int expectedNumber = 10;
        int actualNumber = conversorController.convertToInteger(binary);
        assertEquals(expectedNumber, actualNumber, "The integer conversion is incorrect");
    }

    @Test
    void testConvertToIntegerWithInvalidBinary() {
        String invalidBinary = "102";
        assertThrows(NumberFormatException.class, () -> {
            conversorController.convertToInteger(invalidBinary);
        }, "Expected NumberFormatException for invalid binary input");
    }

    @Test
    void testConvertToRomanV() {
        int number = 5;
        String expectedRoman = "V";
        String actualRoman = conversorController.convertToRoman(number);
        assertEquals(expectedRoman, actualRoman, "The Roman numeral conversion is incorrect");
    }

    @Test
    void testConvertToRomanI() {
        int number = 1;
        String expectedRoman = "I";
        String actualRoman = conversorController.convertToRoman(number);
        assertEquals(expectedRoman, actualRoman, "The Roman numeral conversion is incorrect");
    }

    @Test
    void testConvertToRomanX() {
        int number = 10;
        String expectedRoman = "X";
        String actualRoman = conversorController.convertToRoman(number);
        assertEquals(expectedRoman, actualRoman, "The Roman numeral conversion is incorrect");
    }

    @Test
    void testConvertToRomanC() {
        int number = 100;
        String expectedRoman = "C";
        String actualRoman = conversorController.convertToRoman(number);
        assertEquals(expectedRoman, actualRoman, "The Roman numeral conversion is incorrect");
    }

    @Test
    void testConvertToRoman() {
        int number = 2025;
        String expectedRoman = "MMXXV";
        String actualRoman = conversorController.convertToRoman(number);
        assertEquals(expectedRoman, actualRoman, "The Roman numeral conversion is incorrect");
    }
}

