package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);
    List<Question> findByTheme(String theme);
    Optional<Question> findById(Long id);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.questionAlternativeList")
    List<Question> findAllQuestionsWithAlternatives();

}