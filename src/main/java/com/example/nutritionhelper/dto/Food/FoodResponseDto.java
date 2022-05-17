package com.example.nutritionhelper.dto.Food;

import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.food.foodNutrientAmount.FoodNutrientAmount;
import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FoodResponseDto {
    private Long foodId;
    private String brand;
    private String name;
    private String content;
    private Long price;
    private String link;
    private String image;
    private String amount;
    private List<FoodNutrientAmount> nutrientAmounts = new ArrayList<>();

    public FoodResponseDto(Food food){
        this.foodId = food.getFoodId();
        this.brand = food.getBrand().getBrandNameKor();
        this.name = food.getName();
        this.content = food.getContent();
        this.price = food.getPrice();
        this.link = food.getLink();
        this.image = food.getImage();
        this.amount = food.getAmount();

        this.nutrientAmounts = food.getNutrientAmounts();
    }
}