package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.QuestionAlternative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionAlternativeRepository extends JpaRepository<QuestionAlternative, Long> {

    List<QuestionAlternative> findByQuestionId(Long questionId);
    Optional<QuestionAlternative> findQuestionById(Long id);
    Optional<QuestionAlternative> findByQuestionIdAndItsCorrect(Long id, Boolean itsCorrect);
}
