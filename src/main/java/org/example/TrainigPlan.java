package org.example;

import java.util.List;

abstract class TrainigPlan {
    private String name;
    private List<String> muscleGroups;

    public TrainigPlan(String name) {
        this.name = name;
    }
}
