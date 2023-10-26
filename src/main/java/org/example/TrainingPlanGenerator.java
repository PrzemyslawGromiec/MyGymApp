package org.example;

import java.text.DecimalFormat;
import java.util.*;

public class TrainingPlanGenerator {
    private Scanner scanner = new Scanner(System.in);
    private List<MuscleGroup> muscleGroups = new ArrayList<>();
    private List<Exercise> chestExercises = new ArrayList<>();
    private List<Exercise> backExercises = new ArrayList<>();
    private List<Exercise> legsExercises = new ArrayList<>();
    private List<Exercise> shouldersExercises = new ArrayList<>();
    private List<List<Exercise>> availableTrainingPlans = new ArrayList<>();
    private ExerciseFactory exerciseFactory;
    private List<Exercise> customTrainingPlanGenerated = new ArrayList<>();

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
            customTrainingPlanGenerated.addAll(generateCustomTrainingPlan(selectedMuscleGroups, numOfExercises));
            displayCustomTrainingPlan(customTrainingPlanGenerated);

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

    public void displayCustomTrainingPlan(List<Exercise> trainingPlan) {
        System.out.println("Your custom training plan:");

        for (Exercise exercise : trainingPlan) {
            System.out.println("- " + exercise.getName());
        }
    }

    public void displayMyPlan() {
        if (customTrainingPlanGenerated.isEmpty()) {
            System.out.println("Custom training plan has not been generated yet.");
            return;
        }
        System.out.println("Your personal training plan:");
        for (Exercise exercise : customTrainingPlanGenerated) {
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

    public List<WorkoutResult> addWorkoutResult() {
        List<WorkoutResult> myCompletedWorkout = new ArrayList<>();

        do {
            System.out.println("What muscle group have you trained today?");
            System.out.println("You could choose from: ");
            displayAvailableMuscleGroups();
            String userInput = scanner.nextLine();
            String[] trainedMuscleGroups = userInput.split(",");

            for (String number : trainedMuscleGroups) {
                int muscleChoice = Integer.parseInt(number.trim());
                if (muscleChoice >= 1 && muscleChoice <= muscleGroups.size()) {
                    MuscleGroup selectedMuscleGroup = muscleGroups.get(muscleChoice - 1);

                    List<Exercise> availableExercises = exerciseFactory.getAvailableExercises(selectedMuscleGroup.getName());

                    System.out.println("Available exercises for " + selectedMuscleGroup.getName() + ":");
                    for (int i = 0; i < availableExercises.size(); i++) {
                        System.out.println((i + 1) + ". " + availableExercises.get(i).getName());
                    }

                    System.out.print("Select an exercise (enter exercise number): ");
                    int exerciseChoice = Integer.parseInt(scanner.nextLine());

                    if (exerciseChoice >= 1 && exerciseChoice <= availableExercises.size()) {
                        Exercise selectedExercise = availableExercises.get(exerciseChoice - 1);

                        System.out.print("Enter the number of sets: ");
                        int sets = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter the number of repetitions: ");
                        int repetitions = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter the weight used (in kg): ");
                        double weight = Double.parseDouble(scanner.nextLine());

                        WorkoutResult completedExercise = new WorkoutResult(selectedExercise, sets, repetitions, weight);
                        myCompletedWorkout.add(completedExercise);

                    } else {
                        System.out.println("Invalid exercise selection. Please try again.");
                    }

                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            }

            System.out.print("Do you want to add another exercise? (yes/no): ");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));

        return myCompletedWorkout;
    }

    public void displayCompletedWorkout(List<WorkoutResult> completedWorkout) {
        System.out.println("Completed Workout:");

        for (WorkoutResult result : completedWorkout) {
            System.out.println("Exercise: " + result.getExercise().getName());
            System.out.println("Muscle Group: " + result.getExercise().getMuscleGroup().getName());
            System.out.println("Sets: " + result.getSets());
            System.out.println("Repetitions: " + result.getRepetitions());
            System.out.println("Weight (kg): " + result.getWeight());
            System.out.println();
        }
    }

    public double computeUserBMI() {
        System.out.println("Let's compute your current BMI");

        double height = 0;
        double weight = 0;

        while (true) {
            System.out.println("What is your height (in cm): ");
            if (scanner.hasNextDouble()) {
                height = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number for height.");
                scanner.next();
            }
        }

        while (true) {
            System.out.println("What is your body weight (in kg): ");
            if (scanner.hasNextDouble()) {
                weight = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number for weight.");
                scanner.next();
            }
        }

        double heightInM = height / 100;
        double currentBMI = (weight / (Math.pow(heightInM, 2)));

        DecimalFormat df = new DecimalFormat("#.##");
        String roundedNum = df.format(currentBMI);
        double result = Double.parseDouble(roundedNum);
        System.out.println("Your current BMI is: " + result);
        return result;
    }

    public void BMIRange(double result) {
        if (result < 18.5) {
            System.out.println("BMI Range: Underweight");
        } else if (result >= 18.5 && result < 24.9) {
            System.out.println("BMI Range: Normal Weight");
        } else if (result >= 25 && result < 29.9) {
            System.out.println("BMI Range: Overweight");
        } else {
            System.out.println("BMI Range: Obesity");
        }
    }
}
