package com.example.nutritionhelper.service.supplement;

import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplementService {
    private final SupplementRepository supplementRepository;

    public List<Supplement> getSupplementList(Pageable pageable){
        List<Supplement> supplements = supplementRepository.findAll();
        if(supplements == null){
            throw new IllegalStateException("영양제가 존재하지 않습니다.");
        }else{
            return supplements;
        }
    }

}
