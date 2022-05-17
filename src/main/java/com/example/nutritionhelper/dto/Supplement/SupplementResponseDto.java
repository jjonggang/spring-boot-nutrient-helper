package com.example.nutritionhelper.dto.Supplement;

import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SupplementResponseDto {
    private Long supplementId;
    private String brand;
    private String name;
    private String content;
    private Long price;
    private String link;
    private String image;
    private Long dayTerm;

    private List<SupplementNutrientAmountResponseDto> nutrientAmounts = new ArrayList<>();

    public SupplementResponseDto(Supplement supplement){
        this.supplementId = supplement.getSupplementId();
        this.name = supplement.getName();
        this.brand = supplement.getBrand().getBrandNameKor();
        this.content = supplement.getContent();
        this.price = supplement.getPrice();
        this.link = supplement.getLink();
        this.image = supplement.getImage();
        this.dayTerm = supplement.getDayTerm();
        this.nutrientAmounts = supplement.getNutrientAmounts().stream()
                .map(SupplementNutrientAmountResponseDto::new)
                .collect(Collectors.toList());
    }
}
