package com.example.nutritionhelper.dto.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CombinationSupplementResponseDto {

    private Long combinationItemId;
    private Long combinationId;

    private Supplement supplement;

    public CombinationSupplementResponseDto(CombinationItem combinationItem, Supplement supplement){
        this.combinationItemId = combinationItem.getCombinationItemId();
        this.combinationId = combinationItem.getCombination().getCombinationId();
        this.supplement = supplement;
    }
}