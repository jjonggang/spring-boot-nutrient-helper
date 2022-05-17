package com.example.nutritionhelper.domain.nutrient;

import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Nutrient {
    @Id
    @JsonProperty("nutrient_name_eng")
    private String nutrientNameEng;
    @JoinColumn(name = "nutrient_name_kor")
    private String nutrientNameKor;
    private String unit;
}