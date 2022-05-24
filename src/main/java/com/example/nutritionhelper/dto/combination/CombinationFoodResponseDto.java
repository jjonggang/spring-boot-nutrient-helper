package com.example.nutritionhelper.dto.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.food.food.Food;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CombinationFoodResponseDto {

    private Long combinationItemId;
    private Long combinationId;

    private Food food;

    public CombinationFoodResponseDto(CombinationItem combinationItem, Food food){
        this.combinationItemId = combinationItem.getCombinationItemId();
        this.combinationId = combinationItem.getCombination().getCombinationId();
        this.food = food;
    }

}