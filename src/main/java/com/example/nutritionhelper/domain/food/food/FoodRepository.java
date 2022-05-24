package com.example.nutritionhelper.domain.food.food;

import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByOrderByFoodIdDesc(Pageable pageable);
    List<Food> findAllByOrderByFoodIdDesc();
    List<Food> findByNameContainingIgnoreCase(String keyword);

    List<Food> findByNameContaining(@Param("keyword")String keyword);
    //findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase
}
