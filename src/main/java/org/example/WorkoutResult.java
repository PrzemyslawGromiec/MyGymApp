package org.example;

public class WorkoutResult {
    private Exercise exercise;
    private int sets;
    private int repetitions;
    private double weight;

    public WorkoutResult(Exercise exercise, int sets, int repetitions, double weight) {
        this.exercise = exercise;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getWeight() {
        return weight;
    }


}
