package com.example.nutritionhelper.domain.combination.combinationItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.food.food.Food;
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

    @JsonProperty("supplement_id")
    private Long supplementId;

    @JsonProperty("food_id")
    private Long foodId;
}
