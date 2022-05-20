package com.example.nutritionhelper.domain.supplement.supplement;

import com.example.nutritionhelper.domain.supplement.SupplementNutrientAmount.SupplementNutrientAmount;
import com.example.nutritionhelper.domain.supplement.supplementBrand.SupplementBrand;
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
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("supplement_id")
    private Long supplementId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand")
    private SupplementBrand brand;
    private String name;
    private String content;
    private Long price;
    private String link;
    private String image;
    @JsonProperty("day_term")
    private Long dayTerm;

    @OneToMany(mappedBy = "supplement", cascade = CascadeType.ALL)
    private List<SupplementNutrientAmount> nutrientAmounts = new ArrayList<>();
}
