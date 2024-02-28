package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.QuestionWithoutResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;
    private final QuestionChoicesMapper questionChoicesMapper;
    private final ThemeMapper themeMapper;

    public QuestionMapper(ModelMapper modelMapper, QuestionChoicesMapper alternativeMapper, ThemeMapper themeMapper) {
        this.modelMapper = modelMapper;
        this.questionChoicesMapper = alternativeMapper;
        this.themeMapper = themeMapper;
    }

    public QuestionDTO toDTO(Question entity) {
        QuestionDTO dto = modelMapper.map(entity, QuestionDTO.class);
        dto.setAlternativeDTOList(questionChoicesMapper.toListDTO(entity.getAlternativeList()));
        dto.setTheme(themeMapper.toDTO(entity.getTheme()));
        return dto;
    }

    public QuestionWithoutResponseDTO toDTOWithoutResponse(Question entity) {
        QuestionWithoutResponseDTO dto = modelMapper.map(entity, QuestionWithoutResponseDTO.class);
        dto.setAlternativeDTOList(questionChoicesMapper.toListDTO(entity.getAlternativeList()));
        dto.setTheme(themeMapper.toDTO(entity.getTheme()));
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
