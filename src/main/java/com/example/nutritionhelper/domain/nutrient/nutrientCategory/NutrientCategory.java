package com.example.nutritionhelper.domain.nutrient.nutrientCategory;

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
public class NutrientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("nutrient_category_id")
    private Long nutrientCategoryId;
    @JsonProperty("category_name_kor")
    private String categoryNameKor;
    @JsonProperty("category_name_eng")
    private String categoryNameEng;
}
