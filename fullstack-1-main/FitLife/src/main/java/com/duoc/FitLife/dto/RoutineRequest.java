package com.duoc.FitLife.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoutineRequest {

    @Size(min = 2, max = 50, message = "Nombre entre 2 y 50 caracteres")
    @NotBlank(message = "Nombre del ejercicio obligatorio")
    private String routineName;

     @NotNull(message = "El nivel de dificultad es obligatorio")
    @Min(value = 1, message = "La dificultad debe ser al menos 1")
    @Max(value = 5, message = "La dificultad debe ser mayor a 5")
    private Integer difficultyLevel;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duracion debe ser un numero positivo")
    private double duration;

    @NotBlank(message = "Tipo de entrenamiento obligatorio")
    @Pattern(regexp = "^(?i)(aerobic|anaerobic)$", message = "El tipo debe ser 'aerobic' o 'anaerobic'")
    private String trainingType;
    
    @NotBlank(message = "Nombre obligatorio")
    @Size(min = 2, max = 100, message = "Nombre entre 2 y 100 caracteres")
    private String personalTrainer;
}
