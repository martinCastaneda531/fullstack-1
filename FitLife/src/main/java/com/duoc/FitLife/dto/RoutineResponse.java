package com.duoc.FitLife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResponse {

    private Integer id; //PK
    private String routineName;
    private Integer difficultyLevel; //1 to 5
    private double duration; // duration in hours
    private String trainingType; // aerobic or anaerobic
    private String personalTrainer; // trainer

}
