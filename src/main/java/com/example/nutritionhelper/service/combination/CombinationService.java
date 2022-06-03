package com.example.nutritionhelper.service.combination;

import com.example.nutritionhelper.domain.combination.Combination;
import com.example.nutritionhelper.domain.combination.CombinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CombinationService {

    private final CombinationRepository combinationRepository;

    public Combination createCombination(Long userId){
        Combination combination = Combination.builder()
                .userId(userId)
                .build();
        return combinationRepository.save(combination);
    }

    public boolean checkIfExists(Long userId) {
        return combinationRepository.existsByUserId(userId);
    }

    public Combination findCombination(Long userId) {
        Combination combination = combinationRepository.findFirstByUserIdOrderByUserIdDesc(userId);
        if(combination == null){
            throw new RuntimeException("combination not exists");
        }else{
            return combination;
        }
    }

//    public List<Combination> getOtherCombinations() {
//        Combination combination = combinationRepository.findFirstByUserIdOrderByUserIdDesc(userId);
//        if(combination == null){
//            throw new RuntimeException("combination not exists");
//        }else{
//            return combination;
//        }
//    }
}
