package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.QuestionChoices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionChoicesRepository extends JpaRepository<QuestionChoices, Long> {

    Page<QuestionChoices> findAll(Pageable pageable);

    List<QuestionChoices> findByQuestionId(Long questionId);

    @Query("SELECT COUNT(qa.id) AS alternativeCount, q.theme " +
            "FROM QuestionChoices qa " +
            "JOIN qa.question q " +
            "GROUP BY q.theme")
    List<Object[]> countAlternativesByTheme();

}