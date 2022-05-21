package com.example.nutritionhelper.dto.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CombinationItemResponseDto {
    private Long combinationItemId;
    private Long combinationId;
    private Long supplementId;
    private Long foodId;
    public CombinationItemResponseDto(CombinationItem combinationItem){
        this.combinationItemId = combinationItem.getCombinationItemId();
        this.combinationId = combinationItem.getCombination().getCombinationId();
        this.supplementId = combinationItem.getSupplementId();
        this.foodId = combinationItem.getFoodId();
    }
}
