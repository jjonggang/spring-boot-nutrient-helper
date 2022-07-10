package com.example.nutritionhelper.controller.food;

import com.example.nutritionhelper.dto.Food.FoodResponseDto;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.Supplement.SupplementResponseDto;
import com.example.nutritionhelper.dto.search.SearchDto;
import com.example.nutritionhelper.service.food.FoodService;
import com.example.nutritionhelper.service.supplement.SupplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class FoodApiController {
    private final FoodService foodService;
//test2
    @GetMapping("/food/all")
    public ResponseEntity<?> getFoodList(@PageableDefault(page = 0, size=20) Pageable pageable){
        ResponsePageDto<FoodResponseDto> response = foodService.getFoodList(pageable);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/food/search")
    public ResponseEntity<?> searchFoods(@RequestBody SearchDto searchDto){
        ResponsePageDto<FoodResponseDto> response = foodService.searchFoods(searchDto.getKeyword());
        if(response == null){
            return ResponseEntity.ok().body("검색어에 해당하는 식품이 존재하지 않습니다!");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/food/combine-nutrients")
    public ResponseEntity<?> testtest(){
        foodService.combineNutrients();
        return ResponseEntity.ok().body("성공");
    }
}
