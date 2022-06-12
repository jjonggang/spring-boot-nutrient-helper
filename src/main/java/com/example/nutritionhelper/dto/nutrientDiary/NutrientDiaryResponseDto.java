package com.example.nutritionhelper.dto.nutrientDiary;

import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem.NutrientDiaryItem;
import com.example.nutritionhelper.dto.combination.CombinationItemResponseDto;
import com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryItem.NutrientDiaryItemResponseDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientDiaryResponseDto {
    private Long nutrientDiaryId;
    private String title;
    private String content;
    private List<NutrientDiaryItemResponseDto> nutrientDiaryItemResponseDtos;

    public NutrientDiaryResponseDto(NutrientDiary nutrientDiary){
        this.nutrientDiaryId = nutrientDiary.getNutrientDiaryId();
        this.title = nutrientDiary.getTitle();
        this.content = nutrientDiary.getContent();
        this.nutrientDiaryItemResponseDtos = nutrientDiary.getNutrientDiaryItems().stream()
                .map(NutrientDiaryItemResponseDto::new)
                .collect(Collectors.toList());
    }
}
