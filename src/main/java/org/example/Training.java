package org.example;

import java.time.LocalDate;

public class Training {
    private final LocalDate trainingDate;
    private TrainingPlan trainigPlan;

    public Training(LocalDate trainingDate, TrainingPlan trainigPlan) {
        this.trainingDate = trainingDate;
        this.trainigPlan = trainigPlan;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public TrainingPlan getTrainigPlan() {
        return trainigPlan;
    }

    public void setTrainingPlan(TrainingPlan trainigPlan) {
        this.trainigPlan = trainigPlan;
    }
}
