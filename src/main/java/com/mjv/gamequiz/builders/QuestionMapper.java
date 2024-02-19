package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;
    private final AlternativeMapper alternativeMapper;

    public QuestionMapper(ModelMapper modelMapper, AlternativeMapper alternativeMapper) {
        this.modelMapper = modelMapper;
        this.alternativeMapper = alternativeMapper;
    }

    public QuestionDTO toDTO(Question entity) {
        QuestionDTO dto = modelMapper.map(entity, QuestionDTO.class);
        dto.setAlternativeDTOList(alternativeMapper.toListDTO(entity.getAlternativeList()));
        return dto;
    }

    public Question toEntity(QuestionDTO dto) {
        return modelMapper.map(dto, Question.class);
    }

    public List<QuestionDTO> toListDTO(List<Question> entityList) {
        return entityList.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public Page<QuestionDTO> toPageDTO(Page<Question> page) {
        return page.map(this::toDTO);
    }

}
