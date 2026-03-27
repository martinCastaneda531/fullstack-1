package com.duoc.FitLife.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.FitLife.dto.RoutineRequest;
import com.duoc.FitLife.dto.RoutineResponse;
import com.duoc.FitLife.service.RoutineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines")
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<RoutineResponse>> findAll() {
        return ResponseEntity.ok(routineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(routineService.findById(id));
    }

    @GetMapping("/routineName/{routineName}")
    public ResponseEntity<RoutineResponse> findByRoutineName(@PathVariable String routineName) {
        return ResponseEntity.ok(routineService.findByRoutineName(routineName));
    }

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(@Valid @RequestBody RoutineRequest request) {
        RoutineResponse created = routineService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponse> updateRoutine(@PathVariable int id, @Valid @RequestBody RoutineRequest request) {
        return ResponseEntity.ok(routineService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoutineResponse> deleteById(@PathVariable int id) {
        routineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
