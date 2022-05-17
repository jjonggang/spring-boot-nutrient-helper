package com.example.nutritionhelper.domain.food.food;

import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByOrderByFoodIdDesc(Pageable pageable);

    List<Food> findByNameContaining(String keyword);
}
