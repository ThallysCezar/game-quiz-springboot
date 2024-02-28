package com.mjv.gamequiz.mappers;

import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionChoicesMapper {

    private final ModelMapper modelMapper;

    public QuestionChoicesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionChoicesDTO toDTO(QuestionChoices entity) {
        return modelMapper.map(entity, QuestionChoicesDTO.class);
    }

    public QuestionChoices toEntity(QuestionChoicesDTO dto) {
        return modelMapper.map(dto, QuestionChoices.class);
    }

    public List<QuestionChoicesDTO> toListDTO(List<QuestionChoices> modelList) {
        return modelList.stream()
                .map(this::toDTO).toList();
    }

    public Page<QuestionChoicesDTO> toPageDTO(Page<QuestionChoices> page) {
        return page.map(this::toDTO);
    }

}
