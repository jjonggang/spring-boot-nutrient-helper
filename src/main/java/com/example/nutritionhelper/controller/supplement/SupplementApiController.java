package com.example.nutritionhelper.controller.supplement;

import com.example.nutritionhelper.dto.Response.ResponsePageDto;
import com.example.nutritionhelper.dto.Supplement.SupplementResponseDto;
import com.example.nutritionhelper.service.supplement.SupplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class SupplementApiController {
    private final SupplementService supplementService;

    @GetMapping("/supplement/all")
    public ResponseEntity<?> getSupplementList(@PageableDefault(page = 0, size=20) Pageable pageable){
        ResponsePageDto<SupplementResponseDto> response = supplementService.getSupplementList(pageable);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/supplement/search")
    public ResponseEntity<?> searchSupplement(@RequestParam("keyword") String keyword){
        ResponsePageDto<SupplementResponseDto> response = supplementService.searchSupplements(keyword);
        if(response == null){
            return ResponseEntity.ok().body("검색어에 해당하는 영양제가 존재하지 않습니다!");
        }
        return ResponseEntity.ok().body(response);
    }


}
