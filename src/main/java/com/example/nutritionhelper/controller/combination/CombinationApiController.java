package com.example.nutritionhelper.controller.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.combination.CombinationItemListResponseDto;
import com.example.nutritionhelper.dto.combination.CombinationItemRequestDto;
import com.example.nutritionhelper.dto.combination.CombinationItemResponseDto;
import com.example.nutritionhelper.dto.nutrient.NutrientCircleResponseDto;
import com.example.nutritionhelper.dto.nutrient.NutrientMainPageAnalyzeResponseDto;
import com.example.nutritionhelper.dto.nutrient.NutrientResultAnalysisDto;
import com.example.nutritionhelper.service.combination.CombinationService;
import com.example.nutritionhelper.service.combination.combinationItem.CombinationItemService;
import com.example.nutritionhelper.service.user.UserService;
import com.example.nutritionhelper.service.userGroupNutrientService.UserGroupNutrientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class CombinationApiController {

    private final CombinationService combinationService;
    private final CombinationItemService combinationItemService;
    private final UserGroupNutrientService userGroupNutrientService;

    private final UserService userService;
    @GetMapping("/combination/list")
    public ResponseEntity<?> getCombinationItemList(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //?????? ?????????
                userCombination = combinationService.createCombination(userId);
            }else{
                //combinationId ????????????
                userCombination = combinationService.findCombination(userId);
            }

            CombinationItemListResponseDto dto = new CombinationItemListResponseDto(userCombination);

            return ResponseEntity.ok().body(dto);
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @GetMapping("/combination/list/food")
    public ResponseEntity<?> getCombinationFoodItemList(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //?????? ?????????
                userCombination = combinationService.createCombination(userId);
            }else{
                //combinationId ????????????
                userCombination = combinationService.findCombination(userId);
            }

            List<CombinationItem> combinationItems = combinationItemService.findFoodListOfCombinationId(userCombination.getCombinationId());

            List<CombinationItemResponseDto> dtos = combinationItems.stream().map(CombinationItemResponseDto::new).collect(Collectors.toList());

            return ResponseEntity.ok().body(dtos);
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }
    @GetMapping("/combination/list/supplement")
    public ResponseEntity<?> getCombinationSupplementItemList(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //?????? ?????????
                userCombination = combinationService.createCombination(userId);
            }else{
                //combinationId ????????????
                userCombination = combinationService.findCombination(userId);
            }

            List<CombinationItem> combinationItems = combinationItemService.findSupplementListOfCombinationId(userCombination.getCombinationId());

            List<CombinationItemResponseDto> dtos = combinationItems.stream().map(CombinationItemResponseDto::new).collect(Collectors.toList());

            return ResponseEntity.ok().body(dtos);
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @GetMapping("/combination/main/analysis")
    public ResponseEntity<?> mainPageAnalysis(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            User user = userService.getById(userId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                log.info("1-1");
                //?????? ?????????
                userCombination = combinationService.createCombination(userId);
                return ResponseEntity
                        .badRequest()
                        .body("combination??? ???????????? ????????????.");
            }else{
                log.info("1-2");
                //combinationId ????????????
                userCombination = combinationService.findCombination(userId);
                log.info("1-3" + String.valueOf(userCombination));
                if(userCombination.getCombinationItems().size()==0){
                    return ResponseEntity
                            .badRequest()
                            .body("combination??? ???????????? ???????????? ????????????.");
                }
            }
            // 0??? index??? deficiency, 1??? index??? excess
            List<NutrientCircleResponseDto> result = userGroupNutrientService.mainPageDeficiencyAnalysis(userCombination, user);
            List<NutrientCircleResponseDto> deficient = new ArrayList<>();
            List<NutrientCircleResponseDto> excess = new ArrayList<>();
            for(int i=0;i<result.size();i++){
                if(result.get(i).getType()==0){
                    deficient.add(result.get(i));
                }else{
                    excess.add(result.get(i));
                }
            }
            NutrientMainPageAnalyzeResponseDto dto = new NutrientMainPageAnalyzeResponseDto(deficient, excess);
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

    @GetMapping("/combination/all/analysis")
    public ResponseEntity<?> allAnalysis(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            User user = userService.getById(userId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                log.info("1-1");
                //?????? ?????????
                userCombination = combinationService.createCombination(userId);
                return ResponseEntity
                        .badRequest()
                        .body("combination??? ???????????? ????????????.");
            }else{
                log.info("1-2");
                //combinationId ????????????
                userCombination = combinationService.findCombination(userId);
                log.info("1-3" + String.valueOf(userCombination));
                if(userCombination.getCombinationItems().size()==0){
                    return ResponseEntity
                            .badRequest()
                            .body("combination??? ???????????? ???????????? ????????????.");
                }
            }
            // 0??? index??? deficiency, 1??? index??? excess
            List<NutrientResultAnalysisDto> result = userGroupNutrientService.resultAnalysis(userCombination, user);
            return ResponseEntity.ok().body(result);
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
