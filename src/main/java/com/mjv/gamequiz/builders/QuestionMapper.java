package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.dtos.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;
    private final QuestionAlternativeMapper questionAlternativeMapper;

    public QuestionMapper(ModelMapper modelMapper, QuestionAlternativeMapper questionAlternativeMapper){
        this.modelMapper = modelMapper;
        this.questionAlternativeMapper = questionAlternativeMapper;
    }

    public QuestionDTO toDTO(Question entity){
        QuestionDTO dto = modelMapper.map(entity, QuestionDTO.class);
        dto.setQuestionAlternativeDTOList(questionAlternativeMapper.toListDTO(entity.getQuestionAlternativeList()));
        return dto;
    }

    public Question toEntity(QuestionDTO dto){
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
