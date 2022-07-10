package com.example.nutritionhelper.domain.userGroupNutrient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupNutrientRepository extends JpaRepository<UserGroupNutrient, Long> {
    @Query(value = "SELECT * FROM user_group_nutrient WHERE user_group_id=?1", nativeQuery = true)
    List<UserGroupNutrient> findByUserGroupId(Long userGroupId);
}
