package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.UserMapper;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.exceptions.User.UserException;
import com.mjv.gamequiz.exceptions.User.UserNotFoundException;
import com.mjv.gamequiz.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("Service de User")
public class UserServiceTest {

    private UserRepository repository;
    private UserService sut;
    private UserMapper mapper;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(UserRepository.class);
        this.mapper = Mockito.mock(UserMapper.class);
        this.sut = new UserService(mapper, repository);
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void deveRetornarTodosUsuarios() {
        List<User> userList = Arrays.asList(new User(), new User());
        List<UserDTO> userListDTO = Arrays.asList(new UserDTO(), new UserDTO());

        Mockito.when(repository.findAll()).thenReturn(userList);
        Mockito.when(mapper.toListDTO(userList)).thenReturn(userListDTO);

        final var retorno = sut.findAll();

        Assertions.assertEquals(userListDTO.size(), retorno.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Mockito.verify(mapper, Mockito.times(1)).toListDTO(userList);
    }


    @Test
    @DisplayName("Deve lançar UserNotFoundException quando houver erro ao procurar todas as questões por paginas")
    void deveLancarUserNotFoundExceptionErroAoTodosUsuarios() {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        final var excecao = Assertions.assertThrows(UserNotFoundException.class, () ->
                sut.findAll());

        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar um usuário ao procurar por id")
    void deveRetornarUsuarioAoProcurarPorId() {
        final var id = 1L;
        final var userEntity = new User();
        userEntity.setId(id);

        final var userDTO = new UserDTO();
        userDTO.setId(id);

        Mockito.when(repository.findUserById(Mockito.anyLong())).thenReturn(Optional.of(userEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(userEntity)).thenReturn(userDTO);

        final var retorno = sut.findUserById(id);

        Assertions.assertEquals(userEntity.getId(), retorno.getId());
        Mockito.verify(repository, Mockito.times(1)).findUserById(id);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(userEntity);
    }


    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por usuario por id")
    void deveLancarIllegalArgumentExceptionErroAoProcurarUsuarioPorId() {
        final Long id = null;
        Mockito.when(repository.findUserById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findUserById(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }


    @Test
    @DisplayName("Deve lançar UserException quando houver erro ao procurar por usuario por id")
    void deveLancarQuestionExceptionErroAoProcurarUsuarioPorId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(UserNotFoundException.class, () ->
                sut.findUserById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar todos se o usuário existe ou não")
    void deveRetornarUsuariosExistenteSimOuNao() {
        String login = "thallys@hotmail.com";
        Boolean bool = true;
        Mockito.when(repository.existsByLogin(login)).thenReturn(true);

        final var retorno = sut.userExists(login);

        Assertions.assertEquals(bool, retorno);
        Mockito.verify(repository, Mockito.times(1)).existsByLogin(login);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao verificar se o usuário existe")
    void deveLancarIllegalArgumentExceptionErroAoVerificarSeOUsuarioExiste() {
        String login = "thallys@hotmail.com";
        Mockito.when(repository.existsByLogin(login)).thenThrow(new IllegalArgumentException("O usuário não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.userExists(login));

        Assertions.assertEquals("O usuário não pode ser nulo, tente novamente.", excecao.getMessage());
    }

}
