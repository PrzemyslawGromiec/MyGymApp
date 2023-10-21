package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLibrary implements ExerciseFactory{
    @Override
    public List<Exercise> getAvailableExercises(String bodyPart) {
        if(bodyPart.equals("Chest")) {
            return chestWorkout();
        } else if (bodyPart.equals("Back")) {
            return backWorkout();
        } else if (bodyPart.equals("Legs")) {
            return legsWorkout();
        } else if (bodyPart.equals("Shoulders")) {
            return shoulderWorkout();
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

    public List<Exercise> legsWorkout() {
        List<Exercise> exercises = new ArrayList<>();
        MuscleGroup legs = new MuscleGroup("Legs");
        exercises.add(new Exercise("High front squats", legs));
        exercises.add(new Exercise("Lunges", legs));
        exercises.add(new Exercise("Deadlifts", legs));
        exercises.add(new Exercise("Leg Press", legs));
        exercises.add(new Exercise("Bulgarian split squats", legs));
        return exercises;
    }

    public List<Exercise> shoulderWorkout() {
        List<Exercise> exercises = new ArrayList<>();
        MuscleGroup shoulders = new MuscleGroup("Shoulders");
        exercises.add(new Exercise("Overhead press",shoulders));
        exercises.add(new Exercise("Lateral raises", shoulders));
        exercises.add(new Exercise("Front raises", shoulders));
        exercises.add(new Exercise("Face pulls", shoulders));
        exercises.add(new Exercise("Reverse flyes", shoulders));
        return exercises;
    }
}
