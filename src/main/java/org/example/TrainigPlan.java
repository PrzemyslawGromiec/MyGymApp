package org.example;

import java.util.List;

abstract class TrainigPlan {
    //nazwa planu treninggowego - np beginner plan
    private String name;
    private List<String> muscleGroups;

    public TrainigPlan(String name, List<String> muscleGroups) {
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
