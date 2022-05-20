package com.example.nutritionhelper.domain.supplement.supplement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    List<Supplement> findAllByOrderBySupplementIdDesc(Pageable pageable);

    List<Supplement> findByNameContains(String keyword);


    List<Supplement> findByNameContaining(@Param("keyword")String keyword);
}
