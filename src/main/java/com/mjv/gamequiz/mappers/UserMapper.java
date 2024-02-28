package com.mjv.gamequiz.mappers;

import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO toDTO(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public List<UserDTO> toListDTO(List<User> modelList) {
        return modelList.stream()
                .map(this::toDTO).toList();
    }

//To ListEntity
//    public List<User> toList(List<UserDTO> dtosList) {
//        return dtosList.stream()
//                .map(this::toEntity).collect(Collectors.toList());
//    }
}
