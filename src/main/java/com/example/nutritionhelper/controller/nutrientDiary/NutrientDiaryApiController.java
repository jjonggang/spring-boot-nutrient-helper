package com.example.nutritionhelper.controller.nutrientDiary;

import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.dto.Food.FoodResponseDto;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryRequestDto;
import com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryResponseDto;
import com.example.nutritionhelper.dto.search.SearchDto;
import com.example.nutritionhelper.service.nutrientDiary.NutrientDiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class NutrientDiaryApiController {

    private final NutrientDiaryService nutrientDiaryService;

    @PostMapping("/nutrient-diary")
    public ResponseEntity<?> inputNutrientDiary(@AuthenticationPrincipal String strUserId , @RequestBody NutrientDiaryRequestDto requestDto){
        try{
            Long userId = Long.valueOf(strUserId);
            Long savedDiaryId = nutrientDiaryService.insertNutrientDiary(userId, requestDto);
            NutrientDiary savedNutrientDiary = nutrientDiaryService.findNutrientDiary(savedDiaryId);

            return ResponseEntity.ok().body("diary id "+savedDiaryId+"로 저장에 성공했습니다.");
        }catch (Exception e){
            log.info(e.getMessage());
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @GetMapping("/nutrient-diary/list")
    public ResponseEntity<?> getNutrientDiaryList(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            List<NutrientDiary> savedNutrientDiaries = nutrientDiaryService.findNutrientDiaryByUserId(userId);
            List<NutrientDiaryResponseDto> dtos = savedNutrientDiaries.stream().map(NutrientDiaryResponseDto::new).collect(Collectors.toList());
//            NutrientDiaryResponseDto nutrientDiaryResponseDto = new NutrientDiaryResponseDto(savedNutrientDiary);
            return ResponseEntity.ok().body(dtos);
        }catch (Exception e){
            log.info(e.getMessage());
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

}
