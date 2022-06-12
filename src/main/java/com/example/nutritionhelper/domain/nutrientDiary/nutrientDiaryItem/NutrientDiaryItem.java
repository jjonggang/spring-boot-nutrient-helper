package com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NutrientDiaryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("nutrient_diary_item_id")
    private Long nutrientDiaryItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_diary_id")
//    @JsonProperty("nutrient_diary_id")
    private NutrientDiary nutrientDiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("supplement_id")
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("food_id")
    @JoinColumn(name = "food_id")
    private Food food;
}
