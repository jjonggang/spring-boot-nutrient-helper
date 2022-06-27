package com.example.nutritionhelper.dto.nutrient;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientMainPageAnalyzeResponseDto {
    List<NutrientCircleResponseDto> deficiency;
    List<NutrientCircleResponseDto> excess;

    public NutrientMainPageAnalyzeResponseDto(List<NutrientCircleResponseDto> deficiency, List<NutrientCircleResponseDto> excess){
        this.deficiency = deficiency;
        this.excess = excess;

    }

}
