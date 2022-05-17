package com.example.nutritionhelper.domain.supplement.supplement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    List<Supplement> findAllByOrderBySupplementIdDesc(Pageable pageable);

    List<Supplement> findByNameContaining(String keyword);
}
