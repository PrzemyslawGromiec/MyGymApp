package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLibrary implements ExerciseFactory{
    @Override
    public List<Exercise> getAvialableExercises(String bodyPart) {
        if(bodyPart.equals("Chest")) {
            return chestWorkout();
        } else if (bodyPart.equals("Back")) {
            return backWorkout();
        }
        return new ArrayList<>();
    }

    public List<Exercise> chestWorkout() {
        List<Exercise> exercises = new ArrayList<>();
        MuscleGroup chest = new MuscleGroup("Chest");
        exercises.add(new Exercise("Barbell bench press", chest));
        exercises.add(new Exercise("Push up", chest));
        exercises.add(new Exercise("Chest dip", chest));
        exercises.add(new Exercise("Chest fly", chest));
        exercises.add(new Exercise("Dumbbell bench press", chest));
        return exercises;
    }

    public List<Exercise> backWorkout() {
        List<Exercise> exercises = new ArrayList<>();
        MuscleGroup back = new MuscleGroup("Back");
        exercises.add(new Exercise("Deadlift", back));
        exercises.add(new Exercise("Pull-ups", back));
        exercises.add(new Exercise("Seated row", back));
        exercises.add(new Exercise("Lat pull-down", back));
        exercises.add(new Exercise("Dumbbell pull-over", back));
        return exercises;
    }
}
