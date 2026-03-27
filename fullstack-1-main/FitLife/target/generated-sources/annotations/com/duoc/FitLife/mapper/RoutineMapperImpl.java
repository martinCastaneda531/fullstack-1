package com.duoc.FitLife.mapper;

import com.duoc.FitLife.dto.RoutineRequest;
import com.duoc.FitLife.dto.RoutineResponse;
import com.duoc.FitLife.model.Routine;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-27T09:56:49-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class RoutineMapperImpl implements RoutineMapper {

    @Override
    public Routine toModel(RoutineRequest request) {
        if ( request == null ) {
            return null;
        }

        Routine routine = new Routine();

        routine.setDifficultyLevel( request.getDifficultyLevel() );
        routine.setDuration( request.getDuration() );
        routine.setPersonalTrainer( request.getPersonalTrainer() );
        routine.setRoutineName( request.getRoutineName() );
        routine.setTrainingType( request.getTrainingType() );

        return routine;
    }

    @Override
    public RoutineResponse toResponse(Routine routine) {
        if ( routine == null ) {
            return null;
        }

        RoutineResponse.RoutineResponseBuilder routineResponse = RoutineResponse.builder();

        routineResponse.difficultyLevel( routine.getDifficultyLevel() );
        routineResponse.duration( routine.getDuration() );
        routineResponse.id( routine.getId() );
        routineResponse.personalTrainer( routine.getPersonalTrainer() );
        routineResponse.routineName( routine.getRoutineName() );
        routineResponse.trainingType( routine.getTrainingType() );

        return routineResponse.build();
    }

    @Override
    public List<RoutineResponse> toResponseList(List<Routine> routines) {
        if ( routines == null ) {
            return null;
        }

        List<RoutineResponse> list = new ArrayList<RoutineResponse>( routines.size() );
        for ( Routine routine : routines ) {
            list.add( toResponse( routine ) );
        }

        return list;
    }
}
