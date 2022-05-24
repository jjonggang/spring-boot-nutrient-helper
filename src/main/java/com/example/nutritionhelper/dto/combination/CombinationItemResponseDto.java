package com.example.nutritionhelper.dto.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
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
    private String type;
    private Long combinationItemId;
    private Long combinationId;

    private String image;

    private Long food_id;

    private Long supplement_id;

    public CombinationItemResponseDto(CombinationItem combinationItem){
        this.combinationItemId = combinationItem.getCombinationItemId();
        this.combinationId = combinationItem.getCombination().getCombinationId();
        if(combinationItem.getFood() == null){
            this.type = "s";
            this.food_id = null;
            this.image = combinationItem.getSupplement().getImage();
            this.supplement_id = combinationItem.getSupplement().getSupplementId();
        }else{
            this.type = "f";
            this.image = combinationItem.getFood().getImage();
            this.food_id = combinationItem.getFood().getFoodId();
            this.supplement_id = null;
        }

    }
}
