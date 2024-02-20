package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.AlternativeMapper;
import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.dtos.AlternativeDTO;
import com.mjv.gamequiz.exceptions.AlternativeException;
import com.mjv.gamequiz.repositories.AlternativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;

    private final AlternativeMapper alternativeMapper;

    public Page<AlternativeDTO> findAll(Pageable pageable) {
        Page<Alternative> questionAlternatives = alternativeRepository.findAll(pageable);
        if (questionAlternatives.isEmpty()) {
            throw new AlternativeException("Nenhuma alternativa de pergunta encontrada.");
        }
        return alternativeMapper.toPageDTO(questionAlternatives);
    }

    public AlternativeDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!alternativeRepository.existsById(id)) {
            throw new AlternativeException(String.format("Alternativa não encontrada com o id '%s'.", id));

        }
        return alternativeRepository.findById(id)
                .map(alternativeMapper::toDTO)
                .orElseThrow(() -> new AlternativeException("Erro ao tentar procurar uma alternativa"));
    }

    public List<AlternativeDTO> findByQuestionId(Long questionId) {
        List<Alternative> alternativeList = alternativeRepository.findByQuestionId(questionId);
        if (Objects.isNull(alternativeList) || alternativeList.isEmpty()) {
            throw new IllegalArgumentException("O ID da questão não pode ser nulo, tente novamente.");
        }
        if (!alternativeRepository.existsById(questionId)) {
            throw new AlternativeException(String.format("Questão não encontrada com o id '%s'.", questionId));

        }
        return alternativeList.stream()
                .map(alternativeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlternativeDTO save(AlternativeDTO alternativeDTO) {
        try {
            return alternativeMapper.toDTO(alternativeRepository.save(alternativeMapper.toEntity(alternativeDTO)));
        } catch (AlternativeException exQuestionAlternative) {
            throw new AlternativeException("Erro ao tentar salvar a alternativa");
        }
    }

    public List<AlternativeDTO> saveAll(List<Alternative> alternatives) {
        try {
            List<Alternative> savedAlternatives = alternativeRepository.saveAll(alternatives);
            return savedAlternatives.stream()
                    .map(alternativeMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (AlternativeException exQuestionAlternative) {
            throw new AlternativeException("Erro ao tentar salvar todas as alternativas");
        }
    }

    public List<Object[]> countAlternativesByTheme() {
        return alternativeRepository.countAlternativesByTheme();
    }

}