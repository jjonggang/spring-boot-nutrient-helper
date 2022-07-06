package com.example.nutritionhelper.domain.nutrient;

import com.example.nutritionhelper.domain.nutrient.nutrientCategory.NutrientCategory;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NutrientRepository extends JpaRepository<Nutrient, String> {
    @Query(value = "SELECT * FROM nutrient WHERE category=1", nativeQuery = true)
    List<Nutrient> test();

    @Query(value = "SELECT nutrient_id FROM nutrient WHERE is_analysis=1", nativeQuery = true)
    List<String> findNutrientIdByIsAnalysis();
}
