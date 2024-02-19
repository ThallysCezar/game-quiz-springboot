package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.dtos.AlternativeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlternativeMapper {

    private final ModelMapper modelMapper;

    public AlternativeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AlternativeDTO toDTO(Alternative entity) {
        return modelMapper.map(entity, AlternativeDTO.class);
    }

    public Alternative toEntity(AlternativeDTO dto) {
        return modelMapper.map(dto, Alternative.class);
    }

    public List<AlternativeDTO> toListDTO(List<Alternative> modelList) {
        return modelList.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public Page<AlternativeDTO> toPageDTO(Page<Alternative> page) {
        return page.map(this::toDTO);
    }

}
