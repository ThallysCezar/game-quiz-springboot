package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.UserMapper;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.exceptions.UserException;
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
            throw new UserException("Nenhum usuário encontrado.");
        }

        return userMapper.toListDTO(users);
    }

    public UserDTO findUserById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!userRepository.existsById(id)) {
            throw new UserException(String.format("Usuário não encontrado com o id '%s'.", id));
        }

        return userRepository.findUserById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("Erro ao tentar procurar um usuário"));
    }

    public UserDTO findByEmailAndPassword(String login, String password) {
        if (StringUtils.isEmpty(login) && StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("Os parametros não podem ser nulos, tente novamente.");
        }

        isValidUser(login, password);
        return userRepository.findByLoginAndPassword(login, password)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("Erro ao tentar procurar um usuário"));
    }

    public UserDTO save(UserDTO userDTO) {
        try {
            return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
        } catch (UserException exUser) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
    }

    public boolean isValidUser(String login, String password){
        var usuario = new User();
        usuario.setLogin(login);
        usuario.setPassword(password);
        if (Objects.isNull(userRepository.existsByLoginAndPassword(login, password))) {
            throw new UserException(String.format("Usuário não encontrado com o email: '%s', e password: %s.", login, password));
        }
        return true;
    }

}