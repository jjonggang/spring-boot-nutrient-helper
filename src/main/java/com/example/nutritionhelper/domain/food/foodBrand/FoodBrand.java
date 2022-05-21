package com.example.nutritionhelper.domain.food.foodBrand;

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
public class FoodBrand {
    @Id
    @JsonProperty("brand_id")
    private String brandId;

    @JsonProperty("brand_name_eng")
    private String brandNameEng;

    @JsonProperty("brand_name_kor")
    private String brandNameKor;

    @JsonProperty("brand_link")
    private String brandLink;

    @JsonProperty("brand_ranking")
    private Long brandRanking;
}
