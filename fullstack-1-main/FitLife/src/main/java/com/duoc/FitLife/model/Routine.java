package com.duoc.FitLife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Routine {
    private Integer id; //PK
    private String routineName;
    private Integer difficultyLevel; //1 to 5
    private double duration; // duration in hours
    private String trainingType; // aerobic or anaerobic
    private String personalTrainer; // trainer
}
