package com.example.nutritionhelper.domain.nutrientDiary;

import com.example.nutritionhelper.domain.CreateTimeEntity;
import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem.NutrientDiaryItem;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NutrientDiary extends CreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("nutrient_diary_id")
    private Long nutrientDiaryId;

    @JsonProperty("user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "nutrientDiary", cascade = CascadeType.ALL)
    private List<NutrientDiaryItem> nutrientDiaryItems = new ArrayList<NutrientDiaryItem>();

    public void addItem(NutrientDiaryItem nutrientDiaryItem) {
        log.info("실행7");
        this.nutrientDiaryItems.add(nutrientDiaryItem);
        log.info("실행8");
//        log.info(String.valueOf(nutrientDiaryItem.getNutrientDiaryItemId()));
    }
}
