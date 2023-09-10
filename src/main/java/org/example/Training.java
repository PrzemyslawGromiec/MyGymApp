package org.example;

import java.time.LocalDate;

public class Training {
    private LocalDate trainingDate;
    private TrainigPlan trainigPlan;

    public Training(LocalDate trainingDate, TrainigPlan trainigPlan) {
        this.trainingDate = trainingDate;
        this.trainigPlan = trainigPlan;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public TrainigPlan getTrainigPlan() {
        return trainigPlan;
    }

    public void setTrainigPlan(TrainigPlan trainigPlan) {
        this.trainigPlan = trainigPlan;
    }
}
