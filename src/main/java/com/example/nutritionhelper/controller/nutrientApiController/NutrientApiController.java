package com.example.nutritionhelper.controller.nutrientApiController;

import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.nutrient.NutrientCircleResponseDto;
import com.example.nutritionhelper.dto.nutrient.NutrientMainPageAnalyzeResponseDto;
import com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryResponseDto;
import com.example.nutritionhelper.service.nutrient.NutrientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class NutrientApiController {

    private final NutrientService nutrientService;

    @GetMapping("/nutrient/test")
    public ResponseEntity<?> test(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            List<NutrientCircleResponseDto> circleResponseDtos1 = nutrientService.nutrientAnalyzeTest();
            log.info(String.valueOf(circleResponseDtos1));
            List<NutrientCircleResponseDto> circleResponseDtos2 = nutrientService.nutrientAnalyzeTest();
            log.info(String.valueOf(circleResponseDtos2));

            NutrientMainPageAnalyzeResponseDto dto = new NutrientMainPageAnalyzeResponseDto(circleResponseDtos1, circleResponseDtos2);
            log.info(String.valueOf(dto));
            return ResponseEntity.ok().body(dto);
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
