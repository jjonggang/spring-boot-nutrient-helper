package com.example.nutritionhelper.service.combination.combinationItem;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.CombinationRepository;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItemRepository;
import com.example.nutritionhelper.domain.food.food.FoodRepository;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.nutrient.NutrientRepository;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import com.example.nutritionhelper.dto.combination.CombinationItemRequestDto;
import com.example.nutritionhelper.dto.nutrientAnalysis.NutrientAnalysisDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CombinationItemService {
    private final CombinationItemRepository combinationItemRepository;
    private final CombinationRepository combinationRepository;
    private final NutrientRepository nutrientRepository;
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

    public List<NutrientAnalysisDto> combinationItemCombine(Combination userCombination) {
        List<CombinationItem> combinationItems = userCombination.getCombinationItems();
        log.info("3-1"+combinationItems.toString());
//        List<String> analysisNutrientIds = nutrientRepository.findNutrientIdByIsAnalysis();
        List<Nutrient> analysisNutrients = nutrientRepository.findByIsAnalysis();
        log.info("3-2"+analysisNutrients.toString());


        List<NutrientAnalysisDto> dtos =  analysisNutrients.stream().map(nutrient -> new NutrientAnalysisDto(nutrient)).collect(Collectors.toList());
        log.info("3-3"+dtos.size());

        NutrientAnalysisDto temp;
        Float tempAmount;
        for(int i=0;i<combinationItems.size();i++){
            if(combinationItems.get(i).getFood()!=null){
                log.info("3-4-1"+combinationItems.get(i).getFood().getName());
                for(int j=0;j<combinationItems.get(i).getFood().getNutrientAmounts().size();j++){
                    if(combinationItems.get(i).getFood().getNutrientAmounts().get(j).getNutrient().getIsAnalysis()){
                        for(int k=0;k<dtos.size();k++){
                            if(dtos.get(k).getNutrientId().equals(combinationItems.get(i).getFood().getNutrientAmounts().get(j).getNutrient().getNutrientId())){
                                dtos.get(k).addAmount(combinationItems.get(i).getFood().getNutrientAmounts().get(j).getNutrientAmount());
                                dtos.get(k).addItem(combinationItems.get(i));
                                log.info("3-4"+dtos.get(k).getNutrientId());
                            }
                        }
                    }
                }
            }else{
                log.info("3-4-2"+combinationItems.get(i).getSupplement().getName());
                for(int j=0;j<combinationItems.get(i).getSupplement().getNutrientAmounts().size();j++){
                    log.info("3-4-2-1"+combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getIsAnalysis());
                    if(combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getIsAnalysis()){
                        log.info("3-4-2-2"+combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getIsAnalysis());
                        for(int k=0;k<dtos.size();k++){
                            log.info("비교 nutrient" + dtos.get(k).getNutrientId());
                            log.info("기본 nutrient" + combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getNutrientId());

                            if(dtos.get(k).getNutrientId().equals(combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getNutrientId())){
                                log.info("기본 nutrient" + combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrient().getNutrientId());
                                log.info("양" + combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrientAmount());

                                dtos.get(k).addAmount(combinationItems.get(i).getSupplement().getNutrientAmounts().get(j).getNutrientAmount());
                                dtos.get(k).addItem(combinationItems.get(i));
                                log.info("3-4"+dtos.get(k).getNutrientId());
                            }
                        }
                    }
                }
            }
        }

//        for(int i=0;i<analysisNutrientIds.size();i++){
//            for(int j=0;j<combinationItems.size();j++){
//                if(combinationItems.get(j).getFood()!=null){
//                    combinationItems.get(j).getFood().getNutrientAmounts().
//                }else{
//
//                }
//            }
//        }
        log.info("3-5"+dtos.toString());
        return dtos;

//        if(combinationItems == null){
//            throw new RuntimeException("combination not exists");
//        }else{
//            return combinationItems;
//        }
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
