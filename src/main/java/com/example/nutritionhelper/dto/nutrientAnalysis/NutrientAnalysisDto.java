package com.example.nutritionhelper.dto.nutrientAnalysis;

import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientAnalysisDto {
    String nutrientId;
    Float amount;
    List<CombinationItem> relatedCombinations;
    public NutrientAnalysisDto(String id){
        this.nutrientId = id;
    }

    public void addAmount(Float nutrientAmount) {
        this.amount += nutrientAmount;
    }

    public void addItem(CombinationItem combinationItem) {
        this.relatedCombinations.add(combinationItem);
    }
}
