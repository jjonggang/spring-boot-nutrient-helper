package com.example.nutritionhelper.service.nutrientDiary;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.CombinationRepository;
import com.example.nutritionhelper.domain.combination.combinationItem.CombinationItem;
import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiary;
import com.example.nutritionhelper.domain.nutrientDiary.NutrientDiaryRepository;
import com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem.NutrientDiaryItem;
import com.example.nutritionhelper.domain.nutrientDiary.nutrientDiaryItem.NutrientDiaryItemRepository;
import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.domain.user.UserRepository;
import com.example.nutritionhelper.dto.nutrientDiary.NutrientDiaryRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NutrientDiaryService {
    private final NutrientDiaryItemRepository nutrientDiaryItemRepository;
    private final CombinationRepository combinationRepository;
    private final NutrientDiaryRepository nutrientDiaryRepository;
    private final UserRepository userRepository;
    public Long insertNutrientDiary(Long userId, NutrientDiaryRequestDto requestDto){
        User user = userRepository.findByUserId(userId);
        Combination userCombination = combinationRepository.findByUserId(userId);
        if(userCombination == null){
            throw new RuntimeException("user의 combination이 존재하지 않습니다.");
        }
        List<CombinationItem> combinationItemList = userCombination.getCombinationItems();
        if(combinationItemList == null||combinationItemList.size()==0){
            throw new RuntimeException("아이템 리스트가 비어있습니다.");
        }

        LocalDate now = LocalDate.now();

        if(requestDto.getContent() == null){
            requestDto.setContent(user.getName()+"님의 "+now+" 영양일지");
        }
        if(requestDto.getTitle() == null){
            requestDto.setTitle(user.getName()+"님의 "+"영양일지");
        }

        log.info("실행1");

        NutrientDiary newNutrientDiary = NutrientDiary.builder()
                .content(requestDto.getContent())
                .title(requestDto.getTitle())
                .user(user)
                .build();
        NutrientDiary savedNutrientDiary = nutrientDiaryRepository.save(newNutrientDiary);
        log.info("실행2");
        Long nutrientDiaryId = savedNutrientDiary.getNutrientDiaryId();
        for(int i=0;i<combinationItemList.size();i++){
            log.info("실행3");

            if(combinationItemList.get(i).getFood() != null){
                log.info("실행4");

                NutrientDiaryItem nutrientDiaryItem = NutrientDiaryItem.builder()
                        .food(combinationItemList.get(i).getFood())
                        .nutrientDiary(savedNutrientDiary)
                    .build();
                nutrientDiaryItemRepository.save(nutrientDiaryItem);
//                nutrientDiaryItem.getNutrientDiary().getNutrientDiaryItems().add(nutrientDiaryItem);
            }else if(combinationItemList.get(i).getSupplement() != null){
                log.info("실행5");

                NutrientDiaryItem nutrientDiaryItem = NutrientDiaryItem.builder()
                        .supplement(combinationItemList.get(i).getSupplement())
                        .nutrientDiary(savedNutrientDiary)
                        .build();
                log.info("실행6");
                log.info(String.valueOf(nutrientDiaryItem.getSupplement()));

                nutrientDiaryItemRepository.save(nutrientDiaryItem);
//                nutrientDiaryItem.getNutrientDiary().getNutrientDiaryItems().add(nutrientDiaryItem);
            }else{
                throw new RuntimeException("잘못된 item 존재");
            }
        }
        
        log.info("테스트중");
        return nutrientDiaryId;
    }


    public NutrientDiary findNutrientDiary(Long nutrientDiaryId) {
        return nutrientDiaryRepository.findByNutrientDiaryId(nutrientDiaryId);
    }

    public List<NutrientDiary> findNutrientDiaryByUserId(Long userId) {
        List<NutrientDiary> diaries = nutrientDiaryRepository.findByUser(userRepository.findByUserId(userId));
        if(diaries == null||diaries.size()==0){
            throw new RuntimeException("영양일지가 존재하지 않습니다.");
        }
        return nutrientDiaryRepository.findByUser(userRepository.findByUserId(userId));
    }
}
