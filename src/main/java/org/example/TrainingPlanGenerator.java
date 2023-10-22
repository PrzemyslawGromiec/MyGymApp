package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TrainingPlanGenerator {
    private List<Exercise> chestExercises = new ArrayList<>();
    private List<Exercise> backExercises = new ArrayList<>();
    private List<Exercise> legsExercises = new ArrayList<>();
    private List<Exercise> shouldersExercises = new ArrayList<>();
    private List<List<Exercise>> availableTrainingPlans = new ArrayList<>();
    private ExerciseFactory exerciseFactory;
    private List<MuscleGroup> muscleGroups = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public TrainingPlanGenerator(ExerciseFactory exerciseFactory) {
        this.exerciseFactory = exerciseFactory;
        initDefaultExercises();
        initDefaultMuscleGroups();
    }

    private void initDefaultMuscleGroups() {
        MuscleGroup chest = new MuscleGroup("Chest");
        MuscleGroup back = new MuscleGroup("Back");
        MuscleGroup legs = new MuscleGroup("Legs");
        MuscleGroup shoulders = new MuscleGroup("Shoulders");

        muscleGroups.add(chest);
        muscleGroups.add(back);
        muscleGroups.add(legs);
        muscleGroups.add(shoulders);
    }

    public void addExercise(Exercise exercise, MuscleGroup muscleGroup) {
        switch (muscleGroup.getName()) {
            case "Chest":
                chestExercises.add(exercise);
                break;
            case "Back":
                backExercises.add(exercise);
                break;
            case "Shoulders":
                shouldersExercises.add(exercise);
                break;
            case "Legs":
                legsExercises.add(exercise);
                break;
            default:
                System.out.println("Option not available");
        }
        updateAvailableTrainingPlans();
    }

    private void updateAvailableTrainingPlans() {
        availableTrainingPlans.clear();
        availableTrainingPlans.add(new ArrayList<>(chestExercises));
        availableTrainingPlans.add(new ArrayList<>(backExercises));
        availableTrainingPlans.add(new ArrayList<>(shouldersExercises));
        availableTrainingPlans.add(new ArrayList<>(legsExercises));
    }

    public void initDefaultExercises() {
        chestExercises = generateTrainingPlan("Chest", 5);
        backExercises = generateTrainingPlan("Back", 5);
        shouldersExercises = generateTrainingPlan("Shoulders", 5);
        legsExercises = generateTrainingPlan("Legs", 5);
        updateAvailableTrainingPlans();
    }

    public void displayAvailableTrainingPlans() {
        System.out.println("Available training plans:");

        for (int i = 0; i < availableTrainingPlans.size(); i++) {
            List<Exercise> plan = availableTrainingPlans.get(i);
            System.out.println("Plan " + (i + 1) + " ");
            for (Exercise exercise : plan) {
                System.out.println("- " + exercise.getName());
            }
            System.out.println();
        }
    }

    public void addTrainingPlan(List<Exercise> trainingPlan) {
        availableTrainingPlans.add(trainingPlan);
    }

    public List<Exercise> generateTrainingPlan(String bodyPart, int numOfExercises) {
        List<Exercise> exercises = exerciseFactory.getAvailableExercises(bodyPart);
        if (numOfExercises > exercises.size()) {
            throw new IllegalArgumentException("Number of exercises exceeds available exercises.");
        }
        List<Exercise> selectedExercises = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numOfExercises; i++) {
            int randomIndex = random.nextInt(exercises.size());
            selectedExercises.add(exercises.get(randomIndex));
            exercises.remove(randomIndex);
        }
        return selectedExercises;
    }

    public List<Exercise> getAvailableExercisesForMuscleGroup(MuscleGroup muscleGroup) {
        return switch (muscleGroup.getName()) {
            case "Chest" -> new ArrayList<>(chestExercises);
            case "Back" -> new ArrayList<>(backExercises);
            case "Shoulders" -> new ArrayList<>(shouldersExercises);
            case "Legs" -> new ArrayList<>(legsExercises);
            default -> new ArrayList<>();
        };
    }

    public void displayAvailableTrainingPlansForMuscleGroup(String muscleGroup) {
        List<Exercise> exercises = getAvailableExercisesForMuscleGroup(new MuscleGroup(muscleGroup));
        System.out.println("Available training plans for " + muscleGroup + ":");
        for (Exercise exercise : exercises) {
            System.out.println("- " + exercise.getName());
        }
    }

    public void displayFullBodyWorkout() {
        System.out.println("Full body workout:");
        List<Exercise> fullBodyWorkout = generateFullBodyWorkout();
        for (Exercise exercise : fullBodyWorkout) {
            System.out.println("- " + exercise.getName());
        }
    }

    private List<Exercise> generateFullBodyWorkout() {
        List<Exercise> fullBodyWorkout = new ArrayList<>();
        fullBodyWorkout.addAll(chestExercises.subList(0, Math.min(2, chestExercises.size())));
        fullBodyWorkout.addAll(backExercises.subList(0, Math.min(2, backExercises.size())));
        fullBodyWorkout.addAll(shouldersExercises.subList(0, Math.min(2, shouldersExercises.size())));
        fullBodyWorkout.addAll(legsExercises.subList(0, Math.min(2, legsExercises.size())));
        return fullBodyWorkout;
    }

    public void createCustomTrainingPlan() {
        System.out.println("Let's create a custom training plan!");
        displayAvailableMuscleGroups();
        List<Exercise> customTrainingPlan = new ArrayList<>();

        do {
            System.out.print("Enter the numbers of the muscle groups you want to train (for example: 1,3,4): ");
            String muscleGroupNumbersInput = scanner.nextLine();
            String[] muscleGroupNumbers = muscleGroupNumbersInput.split(",");

            List<MuscleGroup> selectedMuscleGroups = new ArrayList<>();
            for (String number : muscleGroupNumbers) {
                int muscleChoice = Integer.parseInt(number.trim());
                if (muscleChoice >= 1 && muscleChoice <= muscleGroups.size()) {
                    selectedMuscleGroups.add(muscleGroups.get(muscleChoice - 1));
                } else {
                    System.out.println("Invalid selection. Please try again.");
                    return;
                }
            }

            System.out.print("Enter the number of exercises you want for each selected muscle group: ");
            int numOfExercises = scanner.nextInt();
            scanner.nextLine();
            customTrainingPlan.addAll(generateCustomTrainingPlan(selectedMuscleGroups, numOfExercises));
            displayCustomTrainingPlan(customTrainingPlan);

            System.out.println("Do you want to select more muscle groups? (yes/no)");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));
    }

    private List<Exercise> generateCustomTrainingPlan(List<MuscleGroup> muscleGroups, int numOfExercises) {
        List<Exercise> customTrainingPlan = new ArrayList<>();
        Random random = new Random();

        for (MuscleGroup muscleGroup : muscleGroups) {
            List<Exercise> exercises = exerciseFactory.getAvailableExercises(muscleGroup.getName());

            if (numOfExercises > exercises.size()) {
                throw new IllegalArgumentException("Number of exercises exceeds available exercises.");
            }

            for (int i = 0; i < numOfExercises; i++) {
                int randomIndex = random.nextInt(exercises.size());
                customTrainingPlan.add(exercises.get(randomIndex));
                exercises.remove(randomIndex);
            }
        }
        return customTrainingPlan;
    }

    private void displayCustomTrainingPlan(List<Exercise> trainingPlan) {
        System.out.println("Your custom training plan:");

        for (Exercise exercise : trainingPlan) {
            System.out.println("- " + exercise.getName());
        }
    }

    public void displayAvailableMuscleGroups() {
        System.out.println("Available muscle groups:");

        for (int i = 0; i < muscleGroups.size(); i++) {
            MuscleGroup muscleGroup = muscleGroups.get(i);
            System.out.println((i + 1) + ". " + muscleGroup.getName());
        }
    }
}
