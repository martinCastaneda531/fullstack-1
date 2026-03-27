package com.duoc.FitLife.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.duoc.FitLife.dto.RoutineRequest;
import com.duoc.FitLife.dto.RoutineResponse;
import com.duoc.FitLife.model.Routine;

@Mapper(componentModel = "spring")
public interface RoutineMapper {
    
    @Mapping(target = "id", ignore = true)
    Routine toModel(RoutineRequest request);

    RoutineResponse toResponse(Routine routine);

    List<RoutineResponse> toResponseList(List<Routine> routines);
}
