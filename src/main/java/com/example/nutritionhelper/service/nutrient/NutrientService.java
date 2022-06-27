package com.example.nutritionhelper.service.nutrient;

import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.food.foodCategory.FoodCategoryRepository;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.nutrient.NutrientRepository;
import com.example.nutritionhelper.domain.nutrient.nutrientCategory.NutrientCategory;
import com.example.nutritionhelper.domain.nutrient.nutrientCategory.NutrientCategoryRepository;
import com.example.nutritionhelper.dto.Food.FoodResponseDto;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.nutrient.NutrientCircleResponseDto;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NutrientService {
    private final NutrientRepository nutrientRepository;
    private final NutrientCategoryRepository nutrientCategoryRepository;
    public List<NutrientCircleResponseDto> nutrientAnalyzeTest(){
        List<NutrientCircleResponseDto> test = new ArrayList<>();
        List<Nutrient> testNutrients = nutrientRepository.test();

        Collections.shuffle(testNutrients);
        for(int i=0;i<5;i++){
            test.add(new NutrientCircleResponseDto(testNutrients.get(i)));
        }

        return test;
    }
}
