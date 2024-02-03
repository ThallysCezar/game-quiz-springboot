package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTheme(String theme);
    Optional<Question> findById(Long id);
}
