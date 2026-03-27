package com.duoc.FitLife.exception;

public class RoutineNotFoundException extends RuntimeException {

    /**
     * @param id 
     */
    public RoutineNotFoundException(int id) {
        super("No se encontró rutina con el ID: " + id);
    }

    /**
     * @param routineName
     */
    public RoutineNotFoundException(String routineName) {
        super("No se encontró ningún libro con ISBN: " + routineName);
    }
}
