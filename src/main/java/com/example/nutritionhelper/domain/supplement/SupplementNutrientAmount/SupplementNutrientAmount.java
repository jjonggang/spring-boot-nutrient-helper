package com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount;

import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
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
public class SupplementNutrientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("amount_id")
    private Long amountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplement_id")
    private Supplement supplement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_name")
    private Nutrient nutrient;
    @JsonProperty("nutrient_amount")
    private Float nutrientAmount;
}