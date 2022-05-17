package com.example.nutritionhelper.dto.search;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchDto {
    private String keyword;
}
