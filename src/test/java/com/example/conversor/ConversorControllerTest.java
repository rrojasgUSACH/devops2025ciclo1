package com.example.conversor;

import com.example.conversor.controller.ConversorController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ConversorControllerTest {

        @Test
        public void testConvertToBinary() {
            ConversorController controller = new ConversorController();
            int number = 10;
            String expectedBinary = "1010";
            String actualBinary = controller.convertToBinary(number);
            assertEquals(expectedBinary, actualBinary, "The binary conversion is incorrect");
        }

        @Test
        public void testConvertToInteger() {
            ConversorController controller = new ConversorController();
            String binary = "1010";
            int expectedNumber = 10;
            int actualNumber = controller.convertToInteger(binary);
            assertEquals(expectedNumber, actualNumber, "The integer conversion is incorrect");
        }

        @Test
        public void testConvertToBinaryWithZero() {
            ConversorController controller = new ConversorController();
            int number = 0;
            String expectedBinary = "0";
            String actualBinary = controller.convertToBinary(number);
            assertEquals(expectedBinary, actualBinary, "The binary conversion for zero is incorrect");
        }

        @Test
        public void testConvertToIntegerWithInvalidBinary() {
            ConversorController controller = new ConversorController();
            String invalidBinary = "abc";
            assertThrows(NumberFormatException.class, () -> {
                controller.convertToInteger(invalidBinary);
            }, "Expected NumberFormatException for invalid binary input");
        }
}

