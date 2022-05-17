package com.example.nutritionhelper.domain.food.foodCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategory {
    @Id
    @JsonProperty("food_category_eng")
    private String foodCategoryEng;

    @JsonProperty("food_category_kor")
    private String foodCategoryKor;
}
