package com.example.nutritionhelper.service.supplement;

import com.example.nutritionhelper.domain.supplement.supplement.Supplement;
import com.example.nutritionhelper.domain.supplement.supplement.SupplementRepository;
import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.Supplement.SupplementResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupplementService {
    private final SupplementRepository supplementRepository;

    public ResponsePageDto<SupplementResponseDto> getSupplementList(Pageable pageable){
        List<Supplement> supplements = supplementRepository.findAllByOrderBySupplementIdDesc(pageable);
        if(supplements == null){
            throw new IllegalStateException("영양제가 존재하지 않습니다.");
        }else{
            List<SupplementResponseDto> dtos = supplements.stream()
                    .map(supplement -> new SupplementResponseDto(supplement))
                    .collect(Collectors.toList());
            ResponsePageDto<SupplementResponseDto> response = ResponsePageDto.<SupplementResponseDto>builder()
                    .pageCount((long) (dtos.size()/20))
                    .data(dtos)
                    .build();
            return response;
        }
    }

    public List<SupplementResponseDto> searchSupplements(String keyword) {
        List<Supplement> supplements = supplementRepository.findByNameContaining(keyword);
        if(supplements == null){
            return null;
        }
        List<SupplementResponseDto> dtos = supplements.stream()
                .map(SupplementResponseDto::new)
                .collect(Collectors.toList());
//        ResponsePageDto<SupplementResponseDto> response = ResponsePageDto.<SupplementResponseDto>builder()
//                .pageCount((long) (dtos.size()/20))
//                .data(dtos)
//                .build();
        return dtos;
    }
}
