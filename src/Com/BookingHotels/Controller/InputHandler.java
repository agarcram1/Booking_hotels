package com.BookingHotels.Controller;

import Com.BookingHotels.Controller.Messages;

import java.time.LocalDate;
import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getUserOption() {
        int option = 0;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            scanner.nextLine();
        } else {
            Messages.invalidOption();
            scanner.nextLine(); // limpiar el buffer de entrada
        }
        return option;
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            Messages.invalidOption();
            scanner.nextLine(); // limpiar el buffer de entrada
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer de entrada
        return input;
    }

    public static LocalDate getDateInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                Messages.invalidOption();
                System.out.print(prompt);
            }
        }
    }
}