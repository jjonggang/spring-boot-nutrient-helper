package com.example.nutritionhelper.dto.Supplement;

import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SupplementNutrientAmountResponseDto {
    private String nutrientNameEng;
    private String nutrientNameKor;
    private Float nutrientAmount;
    private String unit;

    public SupplementNutrientAmountResponseDto(SupplementNutrientAmount entity){
        Nutrient tempNutrient = entity.getNutrient();
        this.nutrientNameEng = tempNutrient.getNutrientNameEng();
        this.nutrientNameKor = tempNutrient.getNutrientNameKor();
        this.nutrientAmount = entity.getNutrientAmount();
        this.unit = tempNutrient.getUnit();
    }
}
