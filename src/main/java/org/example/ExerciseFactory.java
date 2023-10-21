package org.example;

import java.util.List;

public interface ExerciseFactory {
    List<Exercise> getAvailableExercises(String bodyPart);

}
