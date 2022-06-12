package com.example.nutritionhelper.dto.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.dto.Food.FoodNutrientAmountResponseDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.CropImageFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CombinationItemListResponseDto {

    private List<CombinationItemResponseDto> combinationItems;

    public CombinationItemListResponseDto(Combination combination){
        this.combinationItems = combination.getCombinationItems().stream()
                .map(CombinationItemResponseDto::new)
                .collect(Collectors.toList());
    }
}
