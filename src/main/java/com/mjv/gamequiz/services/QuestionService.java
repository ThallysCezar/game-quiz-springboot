package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.exceptions.QuestionException;
import com.mjv.gamequiz.exceptions.ThemeException;
import com.mjv.gamequiz.repositories.QuestionRepository;
import com.mjv.gamequiz.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ThemeRepository themeRepository;

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
            throw new QuestionException("Nenhuma pergunta encontrada.");
        }
        return questionMapper.toListDTO(questions);
    }

    public QuestionDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!questionRepository.existsById(id)) {
            throw new QuestionException(String.format("Pergunta não encontrado com o id '%s'.", id));
        }

        return questionRepository.findById(id)
                .map(questionMapper::toDTO)
                .orElseThrow(() -> new QuestionException("Erro ao tentar procurar uma questão"));
    }

    public QuestionDTO save(QuestionDTO questionDTO) {
        try {
            Question questionEntity = questionMapper.toEntity(questionDTO);
            Question savedQuestion = questionRepository.save(questionEntity);
            return questionMapper.toDTO(savedQuestion);
        } catch (Exception ex) {
            throw new QuestionException("Erro ao tentar salvar a pergunta.");
        }
    }


    public List<QuestionDTO> getQuestionsByTheme(String themeName) {
        try {
            List<Question> questions = questionRepository.findByThemeName(themeName);
            if(questions.isEmpty()){
                throw new QuestionException("Nenhuma QuestionDTO encontrada para o tema: " + themeName);
            }
            return questionMapper.toListDTO(questions);
        } catch (Exception ex) {
            throw new QuestionException("Erro ao procurar por tema de questões.");
        }
    }

}