package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionAlternativeMapper {

    private final ModelMapper modelMapper;

    public QuestionAlternativeMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public QuestionAlternativeDTO toDTO(QuestionAlternative entity){
        return modelMapper.map(entity, QuestionAlternativeDTO.class);
    }

    public QuestionAlternative toEntity(QuestionAlternativeDTO dto){
        return modelMapper.map(dto, QuestionAlternative.class);
    }

    public ArrayList<QuestionAlternativeDTO> toListDTO(List<QuestionAlternative> modelList) {
        return modelList.stream()
                .map(this::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<QuestionAlternative> toList(List<QuestionAlternativeDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
