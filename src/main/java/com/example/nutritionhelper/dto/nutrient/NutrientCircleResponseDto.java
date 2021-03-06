package com.example.nutritionhelper.dto.nutrient;

import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientCircleResponseDto {
    String nutrientName;
    String circleImage;

    int type;

    public NutrientCircleResponseDto(Nutrient entity){
        this.nutrientName = entity.getNutrientNameKor();
        this.circleImage = entity.getNutrientImage();
    }
    public NutrientCircleResponseDto(Nutrient entity, int type){
        this.nutrientName = entity.getNutrientNameKor();
        this.circleImage = entity.getNutrientImage();
        this.type = type;
    }
}