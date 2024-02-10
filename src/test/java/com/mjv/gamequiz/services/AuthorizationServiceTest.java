package com.mjv.gamequiz.services;

import com.mjv.gamequiz.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@DisplayName("Service de Authorization")
public class AuthorizationServiceTest {
    private UserRepository repository;
    private AuthorizationService sut;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(UserRepository.class);
        this.sut = new AuthorizationService(repository);
    }

    @Test
    @DisplayName("Deve retornar usuario logado para username")
    void deveRetornarLoadUSerByUsername() {
        final var username = "testUser";
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(repository.findByLogin(username)).thenReturn(userDetails);

        final var retorno = sut.loadUserByUsername(username);

        Assertions.assertEquals(userDetails, retorno);
        Mockito.verify(repository, Mockito.times(1)).findByLogin(username);
    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando houver erro ao logar um usuário com o username")
    void deveLancarUsernameNotFoundExceptionErroAoLoadUserByUsername() {
        final var username = "testUser";
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(repository.findByLogin(username)).thenThrow(UsernameNotFoundException.class);

        final var excecao = Assertions.assertThrows(UsernameNotFoundException.class, () ->
                sut.loadUserByUsername(username));

        Mockito.verify(repository, Mockito.times(1)).findByLogin(username);
    }
}
