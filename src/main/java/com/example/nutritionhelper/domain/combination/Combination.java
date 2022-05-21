package com.example.nutritionhelper.domain.combination;

import com.example.nutritionhelper.domain.CreateTimeEntity;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.food.foodNutrientAmount.FoodNutrientAmount;
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
public class Combination extends CreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("combination_id")
    private Long combinationId;

    @JsonProperty("user_id")
    private Long userId;

//    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name="combination_id")
    @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL)
    private List<CombinationItem> combinationItems = new ArrayList<>();
}
