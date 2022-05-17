package com.example.nutritionhelper.domain.food.foodNutrientAmount;

import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FoodNutrientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("amount_id")
    private Long amountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_id")
    private Food food;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_name")
    private Nutrient nutrient;
    @JsonProperty("nutrient_amount")
    private Float nutrientAmount;
}