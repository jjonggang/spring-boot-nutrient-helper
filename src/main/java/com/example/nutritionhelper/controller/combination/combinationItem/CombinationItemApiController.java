package com.example.nutritionhelper.controller.combination.combinationItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.dto.Food.FoodResponseDto;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.User.UserDto;
import com.example.nutritionhelper.dto.combination.CombinationItemListResponseDto;
import com.example.nutritionhelper.dto.combination.CombinationItemRequestDto;
import com.example.nutritionhelper.dto.combination.CombinationItemResponseDto;
import com.example.nutritionhelper.service.combination.CombinationService;
import com.example.nutritionhelper.service.combination.combinationItem.CombinationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class CombinationItemApiController {
    private final CombinationItemService combinationItemService;
    private final CombinationService combinationService;

    @PostMapping("/combination-item")
    public ResponseEntity<?>postCombinationItem(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
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

            CombinationItem userCombinationItem = combinationItemService.create(userId, userCombination.getCombinationId(), combinationItemRequestDto);

            CombinationItemResponseDto dto = new CombinationItemResponseDto(userCombinationItem);

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

    @PostMapping("/combination-item/supplement")
    public ResponseEntity<?>postSupplement(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
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

            CombinationItem userCombinationItem = combinationItemService.create(userId, userCombination.getCombinationId(), combinationItemRequestDto);

            CombinationItemResponseDto dto = new CombinationItemResponseDto(userCombinationItem);

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

    @PostMapping("/combination-item/food")
    public ResponseEntity<?>postFood(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
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

            CombinationItem userCombinationItem = combinationItemService.create(userId, userCombination.getCombinationId(), combinationItemRequestDto);

            CombinationItemResponseDto dto = new CombinationItemResponseDto(userCombinationItem);

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

    @DeleteMapping("/combination-item/supplement")
    public ResponseEntity<?>deleteCombinationItemSupplement(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //새로 만들기
                return ResponseEntity
                        .badRequest()
                        .body("로그인 유저의 조합이 존재하지 않습니다.");
            }else{
                //combinationId 찾아오기
                userCombination = combinationService.findCombination(userId);
            }

            combinationItemService.delete(userCombination.getCombinationId(), combinationItemRequestDto, userId);

            return ResponseEntity.ok().body("삭제 성공");
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @DeleteMapping("/combination-item/food")
    public ResponseEntity<?>deleteCombinationItemFood(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //새로 만들기
                return ResponseEntity
                        .badRequest()
                        .body("로그인 유저의 조합이 존재하지 않습니다.");
            }else{
                //combinationId 찾아오기
                userCombination = combinationService.findCombination(userId);
            }

            combinationItemService.delete(userCombination.getCombinationId(), combinationItemRequestDto, userId);

            return ResponseEntity.ok().body("삭제 성공");
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @DeleteMapping("/combination-item")
    public ResponseEntity<?>deleteCombinationItem(@AuthenticationPrincipal String strUserId, @RequestBody CombinationItemRequestDto combinationItemRequestDto){
        try{
            Long userId = Long.valueOf(strUserId);
            Combination userCombination;
            if(!combinationService.checkIfExists(userId)){
                //새로 만들기
                return ResponseEntity
                        .badRequest()
                        .body("로그인 유저의 조합이 존재하지 않습니다.");
            }else{
                //combinationId 찾아오기
                userCombination = combinationService.findCombination(userId);
            }

            combinationItemService.delete(userCombination.getCombinationId(), combinationItemRequestDto, userId);

            return ResponseEntity.ok().body("삭제 성공");
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @GetMapping("/combination/item")
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

//            List<CombinationItem> combinationItemList = combinationItemService.findUserCombinationItemList(userCombination.getCombinationId());

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
}
