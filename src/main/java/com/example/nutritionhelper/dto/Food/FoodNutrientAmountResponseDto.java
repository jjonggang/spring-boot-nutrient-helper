package com.example.nutritionhelper.dto.Food;

import com.example.nutritionhelper.domain.food.foodNutrientAmount.FoodNutrientAmount;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FoodNutrientAmountResponseDto {
    private String nutrientNameEng;
    private String nutrientNameKor;
    private Float nutrientAmount;
    private String unit;

    public FoodNutrientAmountResponseDto(FoodNutrientAmount entity){
        Nutrient tempNutrient = entity.getNutrient();
        this.nutrientNameEng = tempNutrient.getNutrientNameEng();
        this.nutrientNameKor = tempNutrient.getNutrientNameKor();
        this.nutrientAmount = entity.getNutrientAmount();
        this.unit = tempNutrient.getUnit();
    }
}