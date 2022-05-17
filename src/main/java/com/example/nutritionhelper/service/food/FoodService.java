package com.example.nutritionhelper.service.food;

import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.food.food.FoodRepository;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getFoodList(){
        List<Food> foods = foodRepository.findAll();
        if(foods == null){
            throw new IllegalStateException("식품이 존재하지 않습니다.");
        }else{
            return foods;
        }
    }

}