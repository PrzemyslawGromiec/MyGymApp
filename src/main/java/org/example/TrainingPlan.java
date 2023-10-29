package org.example;

import java.util.List;

abstract class TrainingPlan {
    private String name;
    private List<String> muscleGroups;

    public TrainingPlan(String name, List<String> muscleGroups) {
        this.name = name;
        this.muscleGroups = muscleGroups;
    }

    public String getName() {
        return name;
    }

    public List<String> getMuscleGroups() {
        return muscleGroups;
    }

    abstract void generateWorkout();
}
