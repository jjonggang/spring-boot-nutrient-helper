package com.example.nutritionhelper.domain.combination.combinationItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CombinationItemRepository extends JpaRepository<CombinationItem, Long> {
    @Query(value = "SELECT EXISTS (SELECT * FROM combination_item WHERE supplement_id=?1 AND combination_id=?2)", nativeQuery = true)
    Integer existsBySupplementIdAndCombinationId(Long supplementId, Long combinationId);

    @Query(value = "SELECT EXISTS (SELECT * FROM combination_item WHERE food_id=?1 AND combination_id=?2)", nativeQuery = true)
    Integer existsByFoodIdAndCombinationId(Long foodId, Long combinationId);

//    CombinationItem findByUserIdAndSupplementId(Long userId, Long supplementId);
//
//    CombinationItem findByUserIdAndFoodId(Long userId, Long foodId);

    @Query(value = "SELECT * FROM combination_item WHERE combination_id=?1 AND supplement_id=?2 ORDER BY combination_item_id DESC LIMIT 1", nativeQuery = true)
    CombinationItem findByCombinationIdAndSupplementId(Long combinationId, Long supplementId);

    @Query(value = "SELECT * FROM combination_item WHERE combination_id=?1 AND food_id=?2 ORDER BY combination_item_id DESC LIMIT 1", nativeQuery = true)
    CombinationItem findByCombinationIdAndFoodId(Long combinationId, Long foodId);

    @Query(value = "SELECT * FROM combination_item WHERE combination_id=?1 ORDER BY combination_item_id DESC", nativeQuery = true)
    List<CombinationItem> findByCombinationIdOrderByCombinationItemId(Long combinationId);
    @Query(value = "SELECT * FROM combination_item WHERE combination_id=?1 AND food_id IS NOT NULL ORDER BY combination_item_id DESC", nativeQuery = true)
    List<CombinationItem> findFoodList(Long combinationId);
    @Query(value = "SELECT * FROM combination_item WHERE combination_id=?1 AND supplement_id IS NOT NULL ORDER BY combination_item_id DESC", nativeQuery = true)
    List<CombinationItem> findSupplementList(Long combinationId);
}
