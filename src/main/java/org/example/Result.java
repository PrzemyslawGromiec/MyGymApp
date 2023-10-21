package org.example;

import java.util.List;

public class Result {
    private Exercise exercise;
    private int reps;
    private double weight;

    public Result(Exercise exercise, int reps, double weight) {
        this.exercise = exercise;
        this.reps = reps;
        this.weight = weight;
    }
}

