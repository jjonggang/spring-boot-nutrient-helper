package com.example.nutritionhelper.service.combination.combinationItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.CombinationRepository;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItemRepository;
import com.example.nutritionhelper.domain.food.food.FoodRepository;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import com.example.nutritionhelper.dto.combination.CombinationItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CombinationItemService {
    private final CombinationItemRepository combinationItemRepository;
    private final CombinationRepository combinationRepository;

    private final SupplementRepository supplementRepository;

    private final FoodRepository foodRepository;
    @Transactional
    public CombinationItem create(Long userId, Long combinationId, CombinationItemRequestDto combinationItemRequestDto) {
        CombinationItem combinationItem;
        if(combinationItemRequestDto.getFoodId() == null && combinationItemRequestDto.getSupplementId() == null){
            throw new RuntimeException("모든 값이 null입니다.");
        }
        if(combinationItemRequestDto.getFoodId()!=null && combinationItemRequestDto.getSupplementId()!=null){
            throw new RuntimeException("둘 중 하나의 값만 존재해야합니다.");
        }
        if(combinationItemRequestDto.getFoodId() == null){
            if(combinationItemRepository.existsBySupplementIdAndCombinationId(combinationItemRequestDto.getSupplementId(), combinationId) == 1){
                throw new RuntimeException("이미 존재하는 영양제입니다.");
            }
            Combination combination = combinationRepository.findFirstByUserIdOrderByUserIdDesc(userId);
            combinationItem = CombinationItem.builder()
                    .combination(combination)
                    .supplement(supplementRepository.getById(combinationItemRequestDto.getSupplementId()))
                    .build();
        }else{
            if(combinationItemRepository.existsByFoodIdAndCombinationId(combinationItemRequestDto.getFoodId(), combinationId) == 1){
                throw new RuntimeException("이미 존재하는 식품입니다.");
            }
            Combination combination = combinationRepository.findFirstByUserIdOrderByUserIdDesc(userId);
            combinationItem = CombinationItem.builder()
                    .combination(combination)
                    .food(foodRepository.getById(combinationItemRequestDto.getFoodId()))
                    .build();
        }
        return combinationItemRepository.save(combinationItem);

    }

    @Transactional
    public void delete(Long combinationId, CombinationItemRequestDto combinationItemRequestDto, Long userId) {

        if(combinationItemRequestDto.getFoodId() == null && combinationItemRequestDto.getSupplementId() == null){
            throw new RuntimeException("모든 값이 null입니다.");
        }
        if(combinationItemRequestDto.getFoodId()!=null && combinationItemRequestDto.getSupplementId()!=null){
            throw new RuntimeException("둘 중 하나의 값만 존재해야합니다.");
        }
        CombinationItem deleteCombinationItem;
        if(combinationItemRequestDto.getFoodId() == null){
            deleteCombinationItem = combinationItemRepository.findByCombinationIdAndSupplementId(combinationId, combinationItemRequestDto.getSupplementId());
            combinationItemRepository.delete(deleteCombinationItem);
        }else{
            deleteCombinationItem = combinationItemRepository.findByCombinationIdAndFoodId(combinationId, combinationItemRequestDto.getFoodId());
            combinationItemRepository.delete(deleteCombinationItem);
        }
    }

    public List<CombinationItem> findUserCombinationItemList(Long combinationId) {
        List<CombinationItem> combinationItems = combinationItemRepository.findByCombinationIdOrderByCombinationItemId(combinationId);
        if(combinationItems == null){
            throw new RuntimeException("combination not exists");
        }else{
            return combinationItems;
        }
    }

    public List<CombinationItem> findFoodListOfCombinationId(Long combinationId) {
        List<CombinationItem> items = combinationItemRepository.findFoodList(combinationId);
        if(items == null || items.size()==0){
            throw new RuntimeException("음식 아이템이 존재하지 않습니다.");
        }
        return items;
    }

    public List<CombinationItem> findSupplementListOfCombinationId(Long combinationId) {
        List<CombinationItem> items = combinationItemRepository.findSupplementList(combinationId);
        if(items == null || items.size()==0){
            throw new RuntimeException("영양제 아이템이 존재하지 않습니다.");
        }
        return items;
    }
}
