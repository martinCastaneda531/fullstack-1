package com.duoc.FitLife.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository; 

import com.duoc.FitLife.model.Routine;

@Repository

public class RoutineRepository {
    private final List<Routine> routineList = new ArrayList<>();

    private int sequence = 1;

    public Routine save(Routine routine) {
        routine.setId(sequence++);
        routineList.add(routine);
        return routine;
    }    

    public Optional<Routine> update(Routine routine) {
        return findById(routine.getId()).map(existent -> {
            existent.setRoutineName(routine.getRoutineName());
            existent.setDifficultyLevel(routine.getDifficultyLevel());
            existent.setDuration(routine.getDuration());
            existent.setTrainingType(routine.getTrainingType());
            existent.setPersonalTrainer(routine.getPersonalTrainer());
            return existent;
        });
    }

    public List<Routine> findAll() {
        return List.copyOf(routineList);
    }

    public Optional<Routine> findById(int id) {
        return routineList.stream().filter(routine -> routine.getId() == id).findFirst();
    }

    public Optional<Routine> findByRoutineName(String routineName) {
        return routineList.stream().filter(routine -> routine.getRoutineName().equalsIgnoreCase(routineName)).findFirst();        
    }


    public boolean deleteById(int id) {
        return routineList.removeIf(routine -> routine.getId() == id);
    }
    
}

