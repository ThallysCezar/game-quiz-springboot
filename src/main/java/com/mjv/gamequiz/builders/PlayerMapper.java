package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.dtos.PlayerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;

    public PlayerMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public PlayerDTO toDTO(Player entity){
        return modelMapper.map(entity, PlayerDTO.class);
    }

    public Player toEntity(PlayerDTO dto){
        return modelMapper.map(dto, Player.class);
    }

    public List<PlayerDTO> toListDTO(List<Player> modelList) {
        return modelList.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<Player> toList(List<PlayerDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).collect(Collectors.toList());
    }
}
