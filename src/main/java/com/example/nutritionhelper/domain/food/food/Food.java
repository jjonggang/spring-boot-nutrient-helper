package com.example.nutritionhelper.domain.food.food;

import com.example.nutritionhelper.domain.food.foodBrand.FoodBrand;
import com.example.nutritionhelper.domain.food.foodCategory.FoodCategory;
import com.example.nutritionhelper.domain.food.foodNutrientAmount.FoodNutrientAmount;
import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("food_id")
    private Long foodId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand")
    private FoodBrand brand;
    private String name;
    private String content;
    private Long price;
    private String link;
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private FoodCategory category;
    private String amount;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodNutrientAmount> nutrientAmounts = new ArrayList<>();
}
