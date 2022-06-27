package com.example.nutritionhelper.domain.nutrient.nutrientCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientCategoryRepository extends JpaRepository<NutrientCategory, Long> {

    NutrientCategory findByNutrientCategoryId(long l);
}
