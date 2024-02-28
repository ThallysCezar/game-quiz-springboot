package com.mjv.gamequiz.mappers;

import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.ThemeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThemeMapper {

    private final ModelMapper modelMapper;

    public ThemeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ThemeDTO toDTO(Theme entity) {
        return modelMapper.map(entity, ThemeDTO.class);
    }

    public Theme toEntity(ThemeDTO dto) {
        return modelMapper.map(dto, Theme.class);
    }

    public List<ThemeDTO> toListDTO(List<Theme> modelList) {
        return modelList.stream()
                .map(this::toDTO).toList();
    }

}