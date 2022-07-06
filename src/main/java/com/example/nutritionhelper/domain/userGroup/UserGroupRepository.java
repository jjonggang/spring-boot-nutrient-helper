package com.example.nutritionhelper.domain.userGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query(value = "SELECT * FROM user_group WHERE min_age <= ?1 AND max_age>=?1 AND gender=?2", nativeQuery = true)
    UserGroup findByAgeAndGender(int americanAge, String gender);
}
