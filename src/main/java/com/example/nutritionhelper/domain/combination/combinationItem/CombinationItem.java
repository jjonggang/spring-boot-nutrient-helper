package com.example.nutritionhelper.domain.combination.combinationItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.food.food.Food;
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
public class CombinationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("combination_item_id")
    private Long combinationItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combination_id")
//    @JsonProperty("combination_id")
    private Combination combination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("supplement_id")
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("food_id")
    @JoinColumn(name = "food_id")
    private Food food;
}
