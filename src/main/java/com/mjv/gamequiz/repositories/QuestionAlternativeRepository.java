package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.QuestionAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionAlternativeRepository extends JpaRepository<QuestionAlternative, Long> {

    List<QuestionAlternative> findByQuestionId(Long questionId);

    @Query("SELECT COUNT(qa.id) AS alternativeCount, q.theme " +
            "FROM QuestionAlternative qa " +
            "JOIN qa.question q " +
            "GROUP BY q.theme")
    List<Object[]> countAlternativesByTheme();
}
