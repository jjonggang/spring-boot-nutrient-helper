package com.example.nutritionhelper.controller.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.combination.CombinationItemListResponseDto;
import com.example.nutritionhelper.dto.combination.CombinationItemRequestDto;
import com.example.nutritionhelper.dto.combination.CombinationItemResponseDto;
import com.example.nutritionhelper.service.combination.CombinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class CombinationApiController {

    private final CombinationService combinationService;

    @GetMapping("/combination/list")
    public ResponseEntity<?> getCombinationItemList(@AuthenticationPrincipal String strUserId){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //새로 만들기
                userCombination = combinationService.createCombination(userId);
            }else{
                //combinationId 찾아오기
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

//    @GetMapping("/combination/list")
//    public ResponseEntity<?> getCombinationItemListOfOthers(@AuthenticationPrincipal String strUserId){
//        try{
//            Long userId = Long.valueOf(strUserId);
//
//            List<Combination> combinationList = combinationService.getOtherCombinations();
//
//            CombinationItemListResponseDto dto = new CombinationItemListResponseDto(userCombination);
//
//            return ResponseEntity.ok().body(dto);
//        }catch (Exception e){
//            ResponseDto responseDto = ResponseDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity
//                    .badRequest()
//                    .body(responseDto);
//        }
//    }


}
