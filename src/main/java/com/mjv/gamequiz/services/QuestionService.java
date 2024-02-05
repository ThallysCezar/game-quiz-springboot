package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.exceptions.QuestionException;
import com.mjv.gamequiz.exceptions.UserException;
import com.mjv.gamequiz.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionDTO> findAll() {
        List<Question> questions = questionRepository.findAll();
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
            return questionMapper.toDTO(questionRepository.save(questionMapper.toEntity(questionDTO)));
        } catch (QuestionException exQuestion) {
            throw new QuestionException(String.format("Ocorreu um erro ao tentar salvar a pergunta '%s'.", questionDTO));
        }
    }

}
