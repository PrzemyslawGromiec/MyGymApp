package org.example;

import java.util.Scanner;

public class Application {
    private Scanner scanner = new Scanner(System.in);
    private String userName;


    public void run() {
        int choice;
        welcomeMessage();

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();


        } while (choice !=5);
    }

    private void welcomeMessage() {
        System.out.println("Welcome to the MyGym application!");
        System.out.println("Can I get your name, please?");
        userName = scanner.nextLine();
        System.out.println("Hello " + userName + "! Let's get started.");
    }

    private void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Show available training plans.");
        System.out.println("2. Add the completed workout.");
        System.out.println("3. Add your workout results.");
        System.out.println("4. Calculate your current BMI.");
        System.out.println("5. Exit the application.");
    }
}