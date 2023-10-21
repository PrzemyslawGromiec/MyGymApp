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
                    System.out.println("Would you like to train a specific muscle group or get a full body workout?");
                    System.out.println("1. Train a specific muscle group.");
                    System.out.println("2. Get a full body workout.");
                    int trainingChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (trainingChoice) {
                        case 1:
                            System.out.println("Which muscle group would you like to train?");
                            String muscleGroup = scanner.nextLine();
                            planGenerator.displayAvailableTrainingPlansForMuscleGroup(muscleGroup);
                            break;
                        case 2:
                            planGenerator.displayFullBodyWorkout();
                            break;
                        default:
                            System.out.println("Incorrect choice. Try again.");
                    }
                    //planGenerator.displayAvailableTrainingPlans();
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

    private boolean isValidName(String input) {
        input = input.trim();
        return !input.isEmpty() && input.chars().allMatch(Character::isLetter);
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }

    private void welcomeMessage() {
        System.out.println("Welcome to the MyGym application!");
        System.out.println("Can I get your name, please?");
        while (true) {
            String input = scanner.nextLine();
            if (isValidName(input)) {
                userName = capitalize(input.trim());
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid name.");
            }
        }
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