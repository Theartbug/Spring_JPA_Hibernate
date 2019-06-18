package com.pluralsight.repository;

import com.pluralsight.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// "Long" refers to the return type, which is the Id of Exercise
@Repository("exerciseRepository")
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // dont need any methods since all come with JpaRepository
}
