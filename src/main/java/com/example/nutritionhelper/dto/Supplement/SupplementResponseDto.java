package com.example.nutritionhelper.dto.Supplement;

import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
public class SupplementResponseDto {
    private Long supplementId;
    private String brand;
    private String name;
    private String content;
    private String price;
    private String link;
    private String image;
    private Long dayTerm;
}
