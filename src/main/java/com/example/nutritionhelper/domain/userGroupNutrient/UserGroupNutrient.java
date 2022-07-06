package com.example.nutritionhelper.domain.userGroupNutrient;

import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
import com.example.nutritionhelper.domain.userGroup.UserGroup;
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
public class UserGroupNutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_group_nutrient_id")
    private Long userGroupNutrientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;
    @JsonProperty("average_amount")
    private Float averageAmount;
    @JsonProperty("recommend_amount")
    private Float recommendAmount;
    @JsonProperty("enough_amount")
    private Float enoughAmount;
    @JsonProperty("maximum_amount")
    private Float maximumAmount;
}
