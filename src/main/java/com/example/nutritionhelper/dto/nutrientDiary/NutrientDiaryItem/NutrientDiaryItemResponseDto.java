package com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryItem;

import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem.NutrientDiaryItem;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientDiaryItemResponseDto {
    private Long nutrientDiaryItemId;
    private Long nutrientDiaryId;
    private String type;
    private String image;
    private Long supplementId;
    private Long foodId;
    public NutrientDiaryItemResponseDto(NutrientDiaryItem nutrientDiaryItem){
        this.nutrientDiaryItemId = nutrientDiaryItem.getNutrientDiaryItemId();
        this.nutrientDiaryId = nutrientDiaryItem.getNutrientDiary().getNutrientDiaryId();
        if(nutrientDiaryItem.getFood() == null){
            this.type = "s";
            this.foodId = null;
            this.image = nutrientDiaryItem.getSupplement().getImage();
            this.supplementId = nutrientDiaryItem.getSupplement().getSupplementId();
        }else{
            this.type = "f";
            this.image = nutrientDiaryItem.getFood().getImage();
            this.foodId = nutrientDiaryItem.getFood().getFoodId();
            this.supplementId = null;
        }

    }
}
