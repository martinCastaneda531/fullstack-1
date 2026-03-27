package com.duoc.FitLife.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.duoc.FitLife.dto.RoutineRequest;
import com.duoc.FitLife.dto.RoutineResponse;
import com.duoc.FitLife.exception.RoutineNotFoundException;
import com.duoc.FitLife.mapper.RoutineMapper;
import com.duoc.FitLife.model.Routine;
import com.duoc.FitLife.repository.RoutineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineMapper routineMapper;

    public List<RoutineResponse> findAll() {
        return routineRepository.findAll().stream().map(routineMapper::toResponse).collect(Collectors.toList());
    }

    public RoutineResponse findById(int id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNotFoundException(id));
        return routineMapper.toResponse(routine);
    }

    public RoutineResponse findByRoutineName(String routineName) {
        Routine routine = routineRepository.findByRoutineName(routineName).orElseThrow(() -> new RoutineNotFoundException(routineName));
        return routineMapper.toResponse(routine);
    }

    public RoutineResponse create(RoutineRequest request) {
        Routine routine = routineMapper.toModel(request);
        Routine saved = routineRepository.save(routine);
        return routineMapper.toResponse(saved);
    }

    public RoutineResponse update(int id, RoutineRequest request) {
        Routine routineUpdated = routineMapper.toModel(request);
        routineUpdated.setId(id);
        return routineRepository.update(routineUpdated).map(routineMapper::toResponse).orElseThrow(() -> new RoutineNotFoundException(id));
    }

    public void deleteById(int id) {
        if (!routineRepository.deleteById(id)) {
            throw new RoutineNotFoundException(id);
        }
    }
}
