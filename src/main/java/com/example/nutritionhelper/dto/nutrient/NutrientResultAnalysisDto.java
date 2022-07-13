package com.example.nutritionhelper.dto.nutrient;


import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.userGroupNutrient.UserGroupNutrient;
import com.example.nutritionhelper.dto.nutrientAnalysis.NutrientAnalysisDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientResultAnalysisDto {

    Nutrient nutrient;
    Float averageAmount;
    Float recommendAmount;
    Float enoughAmount;
    Float maximumAmount;
    Float amount;

    public NutrientResultAnalysisDto(UserGroupNutrient userGroupNutrient, NutrientAnalysisDto nutrientAnalysisDto){
        this.nutrient = userGroupNutrient.getNutrient();
        this.averageAmount = userGroupNutrient.getAverageAmount();
        this.recommendAmount = userGroupNutrient.getRecommendAmount();
        this.enoughAmount = userGroupNutrient.getEnoughAmount();
        this.maximumAmount = userGroupNutrient.getMaximumAmount();
        this.amount = nutrientAnalysisDto.getAmount();
    }

}
