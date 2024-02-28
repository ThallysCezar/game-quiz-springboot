package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.UserMapper;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.exceptions.User.UserException;
import com.mjv.gamequiz.exceptions.User.UserNotFoundException;
import com.mjv.gamequiz.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Nenhum usuário encontrado.");
        }

        return userMapper.toListDTO(users);
    }

    public UserDTO findUserById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(String.format("Usuário não encontrado com o id '%s'.", id));
        }

        return userRepository.findUserById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("Erro ao tentar procurar um usuário"));
    }


    public boolean userExists(String login) {
        if (StringUtils.isEmpty(login)) {
            throw new IllegalArgumentException("O usuário não pode ser nulo, tente novamente.");
        }
        return !Boolean.FALSE.equals(userRepository.existsByLogin(login));
    }

}