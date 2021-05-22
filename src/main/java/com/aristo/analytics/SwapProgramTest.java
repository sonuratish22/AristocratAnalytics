package com.aristo.analytics;

import java.util.Scanner;

public class SwapProgramTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Value of firstNumber");
        int firstNumber = scanner.nextInt();
        System.out.println("Please enter value of SecondNumber");
        int secondNumber = scanner.nextInt();
        int c= firstNumber+ secondNumber;
        System.out.println("Sum of firstNumber + SecondNumber is : " + c);

    }
}
