package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.UserMapper;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.exceptions.NoUsersFoundException;
import com.mjv.gamequiz.exceptions.UserNotFoundException;
import com.mjv.gamequiz.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserDTO findUserById(Long id) {
        return userRepository.findUserById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("Erro ao tentar procurar User"));
    }

    public UserDTO save(UserDTO userDTO) {
        if (Objects.nonNull(userDTO)) {
            throw new IllegalArgumentException("UserDTO não pode ser nulo.");
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }


    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoUsersFoundException("Nenhum usuário encontrado.");
        }

        return userMapper.toListDTO(users);
    }

    public UserDTO findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(userMapper::toDTO)
                .orElse(null);
    }
}
