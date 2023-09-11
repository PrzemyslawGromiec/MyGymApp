package org.example;

import java.util.Scanner;

public class Application {
    private Scanner scanner = new Scanner(System.in);
    private String userName;
    private ExerciseLibrary exerciseLibrary = new ExerciseLibrary();
    private TrainingPlanGenerator planGenerator = new TrainingPlanGenerator(exerciseLibrary);


    public void run() {
        int choice;
        welcomeMessage();
        planGenerator.initDefaultExercises();

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    planGenerator.displayAvailableTrainingPlans();
                    //dostepne plany treningowe - TrainingPLanGenerator
                    break;
                case 2:
                    //dodawanie nowego cwiczenia
                    addExercise();
                    break;
                case 3:
                    //swoje wyniki
                    break;
                case 4:
                    //obliczanie bmi
                    break;
                case 5:
                    //wychodzenie
                    break;
                default:
                    System.out.println("Incorrect choince. Try again!");
            }


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

    private void addExercise() {
        System.out.println("What is the exercise name:");
        String exerciseName = scanner.nextLine();
        System.out.println("What is the muscle group (Chest, Back, Arm, Legs):");
        String muscleGroupName = scanner.nextLine();
        MuscleGroup group = new MuscleGroup(muscleGroupName);
        Exercise exercise = new Exercise(exerciseName,group);
        planGenerator.addExercise(exercise,group);
    }
}