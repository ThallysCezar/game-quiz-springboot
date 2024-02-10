package com.mjv.gamequiz.builders;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;

    private final UserMapper userMapper;

    public PlayerMapper(ModelMapper modelMapper, UserMapper userMapper){
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }
    public PlayerDTO toDTO(Player entity){
        PlayerDTO dto = modelMapper.map(entity, PlayerDTO.class);
        dto.setUser(userMapper.toDTO(entity.getUser()));
        return dto;
    }

    public Player toEntity(PlayerDTO dto){
        Player entity = modelMapper.map(dto, Player.class);
        entity.setUser(userMapper.toEntity(dto.getUser()));
        return entity;
    }

    public List<PlayerDTO> toListDTO(List<Player> modelList) {
        return modelList.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

}
