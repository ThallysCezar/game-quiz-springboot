package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;

    public QuestionMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public QuestionDTO toDTO(Question entity){
        return modelMapper.map(entity, QuestionDTO.class);
    }

    public Question toEntity(QuestionDTO dto){
        return modelMapper.map(dto, Question.class);
    }

    public List<QuestionDTO> toListDTO(List<Question> entityList) {
        return entityList.stream()
                .map(this::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Question> toList(List<QuestionDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
