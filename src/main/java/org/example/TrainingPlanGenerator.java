package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingPlanGenerator {
    private List<Exercise> chestExercises = new ArrayList<>();
    private List<Exercise> backExercises = new ArrayList<>();
    private List<Exercise> legsExercises = new ArrayList<>();
    private List<Exercise> shouldersExercises = new ArrayList<>();
    private List<List<Exercise>> availableTrainingPlans = new ArrayList<>();
    private ExerciseFactory exerciseFactory;

    public TrainingPlanGenerator(ExerciseFactory exerciseFactory) {
        this.exerciseFactory = exerciseFactory;
    }

    public void addExercise(Exercise exercise, MuscleGroup muscleGroup) {
        switch (muscleGroup.getName()) {
            case "Chest":
                //todo: nie wyswietla mi nowego cwiczenia
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
        switch (muscleGroup.getName()) {
            case "Chest":
                return new ArrayList<>(chestExercises);
            case "Back":
                return new ArrayList<>(backExercises);
            case "Shoulders":
                return new ArrayList<>(shouldersExercises);
            case "Legs":
                return new ArrayList<>(legsExercises);
            default:
                return new ArrayList<>();
        }
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
}
