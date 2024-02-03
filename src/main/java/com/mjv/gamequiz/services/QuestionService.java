package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.exceptions.QuestionException;
import com.mjv.gamequiz.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionDTO> findAll() {
        return questionMapper.toListDTO(questionRepository.findAll());
    }

    public QuestionDTO findById(Long id) {
        if (id != null && questionRepository.existsById(id)) {
            return questionRepository.findById(id)
                    .map(questionMapper::toDTO)
                    .orElse(null);
        } else {
            throw new QuestionException("Ocorreu um erro ao tentar recuperar a QuestionDTO na consulta findById");
        }
    }

    public QuestionDTO save(QuestionDTO questionDTO) {
        try {
            Question questionEntity = questionRepository.save(questionMapper.toEntity(questionDTO));
            return questionMapper.toDTO(questionEntity);
        } catch (QuestionException exQuestion) {
            throw new QuestionException("Ocorreu um erro ao tentar salvar a QuestionDTO");
        }
    }
}
