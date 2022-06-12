package com.example.nutritionhelper.domain.nutrientDiary;

import com.example.nutritionhelper.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutrientDiaryRepository extends JpaRepository<NutrientDiary, Long> {

    NutrientDiary findTop1ByNutrientDiaryId(Long nutrientDiaryId);

    NutrientDiary findByNutrientDiaryId(Long nutrientDiaryId);

    NutrientDiary findTop1ByUser(User user);

    List<NutrientDiary> findByUser(User byUserId);
}
