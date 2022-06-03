package com.example.nutritionhelper.domain.combination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CombinationRepository extends JpaRepository<Combination, Long> {
    boolean existsByUserId(Long userId);

    Combination findFirstByUserIdOrderByUserIdDesc(Long userId);


}
