package com.example.nutritionhelper.service.food;

import com.example.nutritionhelper.domain.food.food.Food;
import com.example.nutritionhelper.domain.food.food.FoodRepository;
import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import com.example.nutritionhelper.dto.Food.FoodResponseDto;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.Supplement.SupplementResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public ResponsePageDto<FoodResponseDto> getFoodList(Pageable pageable){
        List<Food> foods = foodRepository.findAllByOrderByFoodIdDesc(pageable);
        if(foods == null){
            throw new IllegalStateException("식품이 존재하지 않습니다.");
        }else{
            List<FoodResponseDto> dtos = foods.stream()
                    .map(food -> new FoodResponseDto(food))
                    .collect(Collectors.toList());
            ResponsePageDto<FoodResponseDto> response = ResponsePageDto.<FoodResponseDto>builder()
                    .pageCount((long) (dtos.size()/20))
                    .data(dtos)
                    .build();
            return response;
        }
    }

    public ResponsePageDto<FoodResponseDto> searchFoods(String keyword) {
        List<Food> foods = foodRepository.findByNameContainingIgnoreCase(keyword);
        if(foods == null){
            return null;
        }
        List<FoodResponseDto> dtos = foods.stream()
                .map(food -> new FoodResponseDto(food))
                .collect(Collectors.toList());
        ResponsePageDto<FoodResponseDto> response = ResponsePageDto.<FoodResponseDto>builder()
                .pageCount((long) (dtos.size()/20))
                .data(dtos)
                .build();
        return response;
    }

}