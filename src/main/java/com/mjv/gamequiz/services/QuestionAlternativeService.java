package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionAlternativeMapper;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.exceptions.QuestionAlternativeException;
import com.mjv.gamequiz.repositories.QuestionAlternativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionAlternativeService {

    private final QuestionAlternativeRepository questionAlternativeRepository;

    private final QuestionAlternativeMapper questionAlternativeMapper;

    public Page<QuestionAlternativeDTO> findAll(Pageable pageable) {
        Page<QuestionAlternative> questionAlternatives = questionAlternativeRepository.findAll(pageable);
        if (questionAlternatives.isEmpty()) {
            throw new QuestionAlternativeException("Nenhuma alternativa de pergunta encontrada.");
        }
        return questionAlternativeMapper.toPageDTO(questionAlternatives);
    }

    public QuestionAlternativeDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (questionAlternativeRepository.existsById(id)) {
            throw new QuestionAlternativeException(String.format("Alternativa não encontrada com o id '%s'.", id));

        }
        return questionAlternativeRepository.findById(id)
                .map(questionAlternativeMapper::toDTO)
                .orElseThrow(() -> new QuestionAlternativeException("Erro ao tentar procurar uma alternativa"));
    }

    public List<QuestionAlternativeDTO> findByQuestionId(Long questionId) {
        List<QuestionAlternative> questionAlternativeList = questionAlternativeRepository.findByQuestionId(questionId);
        if (Objects.isNull(questionAlternativeList)) {
            throw new IllegalArgumentException("O ID da questão não pode ser nulo, tente novamente.");
        }
        if (questionAlternativeRepository.existsById(questionId)) {
            throw new QuestionAlternativeException(String.format("Questão não encontrada com o id '%s'.", questionId));

        }
        return questionAlternativeList.stream()
                .map(questionAlternativeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionAlternativeDTO save(QuestionAlternativeDTO questionAlternativeDTO) {
        try {
            return questionAlternativeMapper.toDTO(questionAlternativeRepository.save(questionAlternativeMapper.toEntity(questionAlternativeDTO)));
        } catch (QuestionAlternativeException exQuestionAlternative) {
            throw new QuestionAlternativeException("Erro ao tentar salvar a alternativa");
        }
    }

    public List<QuestionAlternativeDTO> saveAll(List<QuestionAlternative> questionAlternatives) {
        try {
            List<QuestionAlternative> savedAlternatives = questionAlternativeRepository.saveAll(questionAlternatives);
            return savedAlternatives.stream()
                    .map(questionAlternativeMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (QuestionAlternativeException exQuestionAlternative) {
            throw new QuestionAlternativeException("Erro ao tentar salvar todas as alternativas");
        }
    }

    public List<Object[]> countAlternativesByTheme() {
        return questionAlternativeRepository.countAlternativesByTheme();
    }

}