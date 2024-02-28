package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.QuestionWithoutResponseDTO;
import com.mjv.gamequiz.exceptions.Question.QuestionException;
import com.mjv.gamequiz.exceptions.Question.QuestionNotFoundException;
import com.mjv.gamequiz.exceptions.Theme.ThemeNotFoundException;
import com.mjv.gamequiz.repositories.QuestionRepository;
import com.mjv.gamequiz.repositories.ThemeRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ThemeRepository themeRepository;
    private final Logger LOGGER = LogManager.getLogger(QuestionService.class);

    public Page<QuestionDTO> findAll(Pageable pageable) {
        Page<Question> questions = questionRepository.findAll(pageable);
        if (questions.isEmpty()) {
            throw new QuestionException("Nenhuma pergunta encontrada.");
        }
        return questionMapper.toPageDTO(questions);
    }

    public List<QuestionDTO> findAllQuestionsWithAlternatives() {
        List<Question> questions = questionRepository.findAllQuestionsWithAlternatives();
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Nenhuma pergunta encontrada.");
        }
        return questionMapper.toListDTO(questions);
    }

    public QuestionDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException();
        }

        return questionRepository.findById(id)
                .map(questionMapper::toDTO)
                .orElseThrow(QuestionException::new);
    }

    public QuestionDTO save(QuestionDTO questionDTO) {
        try {
            Question questionEntity = questionMapper.toEntity(questionDTO);
            final var themeName = questionEntity.getTheme().getTheme();
            if (StringUtils.isNotEmpty(themeName)) {
                Theme theme = themeRepository.findByTheme(themeName)
                        .orElseThrow(ThemeNotFoundException::new);
                questionEntity.setTheme(theme);
                Question savedQuestion = questionRepository.save(questionEntity);
                return questionMapper.toDTO(savedQuestion);
            } else {
                throw new ThemeNotFoundException("Nome do tema está vazio.");
            }
        } catch (Exception ex) {
            LOGGER.error("Erro desconhecido ao salvar a questão: " + ex.getMessage(), ex);
            throw new QuestionException();
        }
    }

    public List<QuestionDTO> getQuestionsByTheme(String themeName) {
        List<Question> questions = questionRepository.findByThemeName(themeName);
        if (questions.isEmpty()) {
            throw new ThemeNotFoundException("Nenhuma QuestionDTO encontrada para o tema: " + themeName);
        }
        return questionMapper.toListDTO(questions);
    }

    public QuestionWithoutResponseDTO getRandomQuestionByTheme(String themeName) {
        List<Question> questions = questionRepository.findByThemeNameWithoutResponse(themeName);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Nenhuma QuestionDTO encontrada para o tema: " + themeName);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        Question randomQuestion = questions.get(randomIndex);

        return questionMapper.toDTOWithoutResponse(randomQuestion);
    }

}