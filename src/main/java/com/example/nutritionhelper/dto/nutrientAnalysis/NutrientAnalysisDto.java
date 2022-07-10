package com.example.nutritionhelper.dto.nutrientAnalysis;

import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
//@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientAnalysisDto {
    String nutrientId;
    Nutrient nutrient;
    Float amount = 0.0f;

    List<CombinationItem> relatedCombinations = new ArrayList<>();
    public NutrientAnalysisDto(String id){
        this.nutrientId = id;
    }

    public NutrientAnalysisDto(Nutrient entity){
        this.nutrientId = entity.getNutrientId();
        this.nutrient = entity;
    }

    public void addAmount(Float nutrientAmount) {
        log.info("nutrient amount 더하기");
        this.amount += nutrientAmount;
        log.info("nutrient amount 더하기완료?"+this.amount);
    }

    public void addItem(CombinationItem combinationItem) {
        log.info("relatedComb");
        this.relatedCombinations.add(combinationItem);
        log.info("relatedComb"+relatedCombinations.get(0).getCombinationItemId());
    }
}
