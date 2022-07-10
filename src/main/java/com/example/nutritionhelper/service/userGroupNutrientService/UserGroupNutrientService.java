package com.example.nutritionhelper.service.userGroupNutrientService;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.domain.userGroup.UserGroup;
import com.example.nutritionhelper.domain.userGroupNutrient.UserGroupNutrient;
import com.example.nutritionhelper.domain.userGroupNutrient.UserGroupNutrientRepository;
import com.example.nutritionhelper.dto.nutrient.NutrientCircleResponseDto;
import com.example.nutritionhelper.dto.nutrientAnalysis.NutrientAnalysisDto;
import com.example.nutritionhelper.service.combination.combinationItem.CombinationItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGroupNutrientService {

    private final CombinationItemService combinationItemService;

    private final UserGroupNutrientRepository userGroupNutrientRepository;

    public List<NutrientCircleResponseDto> mainPageDeficiencyAnalysis(Combination userCombination, User user){
//        List<NutrientCircleResponseDto> test = new ArrayList<>();
        Long userGroupId = user.getUserGroupId();
        log.info("2-1"+userGroupId);
        List<UserGroupNutrient> userGroupNutrients = userGroupNutrientRepository.findByUserGroupId(userGroupId);
        log.info("2-2"+userGroupNutrients.toString());
        List<NutrientCircleResponseDto> result = new ArrayList<>();
        UserGroupNutrient temp;

        List<NutrientAnalysisDto> dtos = combinationItemService.combinationItemCombine(userCombination);
        log.info("2-3"+dtos.toString());

        int current = -1;
        for(int i=0;i<userGroupNutrients.size();i++){
            for (int j=0;j<dtos.size();j++){
                if(dtos.get(j).getNutrientId().equals(userGroupNutrients.get(i).getNutrient().getNutrientId())){
                    current = j;
                    break;
                }
                if(j==dtos.size()-1){
                    throw new RuntimeException("mainpage deficiency analysis error");
                }
            }
            if(current == -1){
                throw new RuntimeException("mainpage deficiency analysis error");
            }
            temp = userGroupNutrients.get(i);
            int n = 0;
            // 1. average만 존재하는 경우
            // ENG
            if(temp.getAverageAmount()!=null&&temp.getRecommendAmount()==null&&temp.getEnoughAmount()==null
                    &&temp.getMaximumAmount()==null){
                n=1;
            }
            // 2. average, recommend만 존재하는 경우
            // recommend보다 적으면 deficiency(0), 많으면 excess(1)
            else if(temp.getAverageAmount()!=null&&temp.getRecommendAmount()!=null&&temp.getEnoughAmount()==null
                    &&temp.getMaximumAmount()==null){
                n=2;
            }
            // 3. enough만 존재하는 경우
            // enough 넘으면 많으면 excess(1)
            else if(temp.getAverageAmount()==null&&temp.getRecommendAmount()==null&&temp.getEnoughAmount()!=null
                    &&temp.getMaximumAmount()==null){
                n=3;
            }
            // 4. average, recommend, maximum만 존재하는 경우
            // recommend보다 적으면 deficiency(0), maximum보다 많으면 excess(1)
            else if(temp.getAverageAmount()!=null&&temp.getRecommendAmount()!=null&&temp.getEnoughAmount()==null
                    &&temp.getMaximumAmount()!=null){
                n=4;
            }
            // 5. enough, maximum만 존재하는 경우
            // maximum보다 많으면 excess(1)
            else if(temp.getAverageAmount()==null&&temp.getRecommendAmount()==null&&temp.getEnoughAmount()!=null
                    &&temp.getMaximumAmount()!=null){
                n=5;
            }

            switch(n) {
                case 0:
                    throw new RuntimeException("분석 오류1");
                case 1:
                    continue;
                case 2:
                    // deficiency(0)
                    if(userGroupNutrients.get(i).getRecommendAmount()>dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 0));
                    }
                    // excess(1)
                    else if(userGroupNutrients.get(i).getRecommendAmount()<dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 1));
                    }
                    continue;
                case 3:
                    // excess(1)
                    if(userGroupNutrients.get(i).getEnoughAmount()<dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 1));
                    }
                    continue;
                case 4:
                    if(userGroupNutrients.get(i).getRecommendAmount()>dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 0));
                    }
                    // excess(1)
                    else if(userGroupNutrients.get(i).getMaximumAmount()<dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 1));
                    }
                    continue;
                case 5:
                    if(userGroupNutrients.get(i).getMaximumAmount()<dtos.get(current).getAmount()){
                        result.add(new NutrientCircleResponseDto(dtos.get(current).getNutrient(), 1));
                    }
                    continue;
                default:
                    break;
            }
        }



//        List<Nutrient> testNutrients = nutrientRepository.test();

//        Collections.shuffle(testNutrients);
//        for(int i=0;i<5;i++){
//            test.add(new NutrientCircleResponseDto(testNutrients.get(i)));
//        }

        return result;
    }
}
