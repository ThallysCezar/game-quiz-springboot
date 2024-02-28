package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    Optional<Question> findById(Long id);

    @Query(value = "SELECT q.* FROM t_question q LEFT JOIN t_question_choices a ON q.id = a.fk_question", nativeQuery = true)
    List<Question> findAllQuestionsWithAlternatives();

    @Query("SELECT q FROM Question q JOIN q.theme t WHERE t.theme LIKE %:themeName%")
    List<Question> findByThemeName(@Param("themeName") String themeName);

    @Query("SELECT q FROM Question q JOIN q.theme t WHERE t.theme LIKE %:themeName%")
    List<Question> findByThemeNameWithoutResponse(@Param("themeName") String themeName);

}