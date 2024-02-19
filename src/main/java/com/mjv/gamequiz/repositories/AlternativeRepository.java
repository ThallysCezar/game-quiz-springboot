package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Alternative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlternativeRepository extends JpaRepository<Alternative, Long> {

    Page<Alternative> findAll(Pageable pageable);
    List<Alternative> findByQuestionId(Long questionId);
    @Query("SELECT COUNT(qa.id) AS alternativeCount, q.theme " +
            "FROM Alternative qa " +
            "JOIN qa.question q " +
            "GROUP BY q.theme")
    List<Object[]> countAlternativesByTheme();

}