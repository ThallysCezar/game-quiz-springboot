package com.mjv.gamequiz.mappers;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.dtos.PlayerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;

    private final UserMapper userMapper;

    public PlayerMapper(ModelMapper modelMapper, UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }

    public PlayerDTO toDTO(Player entity) {
        PlayerDTO dto = modelMapper.map(entity, PlayerDTO.class);
        dto.setUser(userMapper.toDTO(entity.getUser()));
        return dto;
    }

    public Player toEntity(PlayerDTO dto) {
        Player entity = modelMapper.map(dto, Player.class);
        entity.setUser(userMapper.toEntity(dto.getUser()));
        return entity;
    }

    public List<PlayerDTO> toListDTO(List<Player> modelList) {
        return modelList.stream()
                .map(this::toDTO).toList();
    }

    public List<Player> toList(List<PlayerDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).toList();
    }

}