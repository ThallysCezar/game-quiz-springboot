package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionAlternativeMapper;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.exceptions.QuestionAlternativeException;
import com.mjv.gamequiz.repositories.QuestionAlternativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionAlternativeService {

    private final QuestionAlternativeRepository questionAlternativeRepository;

    private final QuestionAlternativeMapper questionAlternativeMapper;


    public List<QuestionAlternativeDTO> findAll() {
        List<QuestionAlternative> questions = questionAlternativeRepository.findAll();
        if (questions.isEmpty()) {
            throw new QuestionAlternativeException("Nenhuma questionAlternative encontrada.");
        }
        return questionAlternativeMapper.toListDTO(questions);
    }

    public QuestionAlternativeDTO findById(Long id) {
        if (id != null && questionAlternativeRepository.existsById(id)) {
            return questionAlternativeRepository.findById(id)
                    .map(questionAlternativeMapper::toDTO)
                    .orElse(null);
        } else {
            throw new QuestionAlternativeException("ID inválido ou não encontrado");
        }
    }

    public QuestionAlternativeDTO save(QuestionAlternative questionAlternative) {
        try {
            QuestionAlternative savedAlternative = questionAlternativeRepository.save(questionAlternative);
            return questionAlternativeMapper.toDTO(savedAlternative);
        } catch (QuestionAlternativeException exQuestionAlternative) {
            throw new QuestionAlternativeException("Erro ao tentar salvar");
        }
    }

    public List<QuestionAlternativeDTO> saveAll(List<QuestionAlternative> questionAlternatives) {
        try {
            List<QuestionAlternative> savedAlternatives = questionAlternativeRepository.saveAll(questionAlternatives);
            return savedAlternatives.stream()
                    .map(questionAlternativeMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (QuestionAlternativeException exQuestionAlternative) {
            throw new QuestionAlternativeException("Erro ao tentar salvar todos");
        }
    }

    public List<QuestionAlternativeDTO> findByQuestionId(Long questionId) {
        List<QuestionAlternative> questionAlternativeList = questionAlternativeRepository.findByQuestionId(questionId);
        return questionAlternativeList.stream()
                .map(questionAlternativeMapper::toDTO)
                .collect(Collectors.toList());
    }

}

