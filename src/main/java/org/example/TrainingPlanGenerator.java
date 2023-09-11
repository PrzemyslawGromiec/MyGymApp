package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingPlanGenerator {
    private List<Exercise> chestExercises = new ArrayList<>();
    private List<Exercise> backExercises = new ArrayList<>();
    private List<Exercise> armExercises = new ArrayList<>();
    private List<Exercise> legsExercise = new ArrayList<>();
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
            case "Arm":
                armExercises.add(exercise);
                break;
            case "Legs":
                legsExercise.add(exercise);
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
        availableTrainingPlans.add(new ArrayList<>(armExercises));
        availableTrainingPlans.add(new ArrayList<>(legsExercise));
    }

    public void initDefaultExercises() {
        chestExercises = generateTrainingPlan("Chest", 4);
        backExercises = generateTrainingPlan("Back", 4);
        //armExercises = generateTrainingPlan("Arm", 4);
        //legsExercise = generateTrainingPlan("Legs", 4);
        //todo: dopisac reszte cwiczen w library
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
        List<Exercise> exercises = exerciseFactory.getAvialableExercises(bodyPart);
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
            case "Arm":
                return new ArrayList<>(armExercises);
            case "Legs":
                return new ArrayList<>(legsExercise);
            default:
                return new ArrayList<>();
        }
    }
}
