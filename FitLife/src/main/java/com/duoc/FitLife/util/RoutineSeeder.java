package com.duoc.FitLife.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.duoc.FitLife.dto.RoutineRequest;
import com.duoc.FitLife.service.RoutineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoutineSeeder implements CommandLineRunner {

    private final RoutineService routineService;

    @Override
    public void run(String... args){
        RoutineRequest routine1 = buildRequest("plank", 2, 1, "anaerobic", "Jorge");
        RoutineRequest routine2 = buildRequest("Sprints", 3, 1.5, "anaerobic", "Luis");
        RoutineRequest routine3 = buildRequest("swim", 1, 0.5, "aerobic", "Pedro");

        routineService.create(routine1);
        routineService.create(routine2);
        routineService.create(routine3);

        System.out.println("1. " + routine1.getRoutineName());
        System.out.println("2. " + routine2.getRoutineName());
        System.out.println("3. " + routine3.getRoutineName());

    }

    private RoutineRequest buildRequest(String routineName, Integer diffultyLevel, double duration, String trainingType, String personalTrainer) {
        RoutineRequest req = new RoutineRequest();
        req.setRoutineName(routineName);
        req.setDifficultyLevel(diffultyLevel);
        req.setDuration(duration);
        req.setTrainingType(trainingType);
        req.setPersonalTrainer(personalTrainer);
        return req;
    }

}