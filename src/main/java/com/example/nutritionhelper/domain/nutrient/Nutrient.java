package com.example.nutritionhelper.domain.nutrient;

import com.example.nutritionhelper.domain.food.foodCategory.FoodCategory;
import com.example.nutritionhelper.domain.nutrient.nutrientCategory.NutrientCategory;
import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Nutrient {
    @Id
    @JsonProperty("nutrient_id")
    private String nutrientId;
    @JsonProperty("nutrient_name_eng")
    private String nutrientNameEng;
    @JsonProperty("nutrient_name_kor")
    private String nutrientNameKor;
    private String unit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private NutrientCategory category;
    @JsonProperty("nutrient_image")
    private String nutrientImage;
}