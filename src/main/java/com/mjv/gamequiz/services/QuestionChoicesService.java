package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionChoicesMapper;
import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesException;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesNotFoundException;
import com.mjv.gamequiz.repositories.QuestionChoicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionChoicesService {

    private final QuestionChoicesRepository questionChoicesRepository;

    private final QuestionChoicesMapper questionChoicesMapper;

    public Page<QuestionChoicesDTO> findAll(Pageable pageable) {
        Page<QuestionChoices> questionAlternatives = questionChoicesRepository.findAll(pageable);
        if (questionAlternatives.isEmpty()) {
            throw new QuestionChoicesNotFoundException("Nenhuma alternativa de pergunta encontrada.");
        }
        return questionChoicesMapper.toPageDTO(questionAlternatives);
    }

    public QuestionChoicesDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!questionChoicesRepository.existsById(id)) {
            throw new QuestionChoicesException(String.format("Alternativa não encontrada com o id '%s'.", id));

        }
        return questionChoicesRepository.findById(id)
                .map(questionChoicesMapper::toDTO)
                .orElseThrow(QuestionChoicesException::new);
    }

    public List<QuestionChoicesDTO> findByQuestionId(Long questionId) {
        List<QuestionChoices> alternativeList = questionChoicesRepository.findByQuestionId(questionId);
        if (Objects.isNull(alternativeList) || alternativeList.isEmpty()) {
            throw new IllegalArgumentException("O ID da questão não pode ser nulo, tente novamente.");
        }
        if (!questionChoicesRepository.existsById(questionId)) {
            throw new QuestionChoicesNotFoundException(String.format("Questão não encontrada com o id '%s'.", questionId));

        }
        return alternativeList.stream()
                .map(questionChoicesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionChoicesDTO save(QuestionChoicesDTO alternativeDTO) {
        try {
            return questionChoicesMapper.toDTO(questionChoicesRepository.save(questionChoicesMapper.toEntity(alternativeDTO)));
        } catch (QuestionChoicesException exQuestionAlternative) {
            throw new QuestionChoicesException("Erro ao tentar salvar a alternativa");
        }
    }

    public List<QuestionChoicesDTO> saveAll(List<QuestionChoices> alternatives) {
        try {
            List<QuestionChoices> savedAlternatives = questionChoicesRepository.saveAll(alternatives);
            return savedAlternatives.stream()
                    .map(questionChoicesMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (QuestionChoicesException exQuestionAlternative) {
            throw new QuestionChoicesException("Erro ao tentar salvar todas as alternativas");
        }
    }

    public List<Object[]> countAlternativesByTheme() {
        return questionChoicesRepository.countAlternativesByTheme();
    }

}