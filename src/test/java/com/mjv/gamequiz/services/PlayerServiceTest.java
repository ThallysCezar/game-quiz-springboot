package com.mjv.gamequiz.services;

import com.mjv.gamequiz.mappers.PlayerMapper;
import com.mjv.gamequiz.mappers.UserMapper;
import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.exceptions.Player.PlayerException;
import com.mjv.gamequiz.exceptions.Player.PlayerNotFoundException;
import com.mjv.gamequiz.factories.PlayerFactory;
import com.mjv.gamequiz.factories.UserFactory;
import com.mjv.gamequiz.repositories.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service de Player")
public class PlayerServiceTest {

    private PlayerRepository repository;
    private PlayerService sut;
    private PlayerMapper mapper;
    private UserMapper userMapper;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(PlayerRepository.class);
        this.userMapper = Mockito.mock(UserMapper.class);
        this.mapper = Mockito.mock(PlayerMapper.class);
        this.sut = new PlayerService(repository, mapper, userMapper);
    }

    @Test
    @DisplayName("Deve retornar todas os players")
    void deveRetornarTodasPlayers() {
        List<Player> userList = Arrays.asList(new Player(), new Player());

        List<PlayerDTO> userListDTO = Arrays.asList(new PlayerDTO(), new PlayerDTO());

        Mockito.when(repository.findAll()).thenReturn(userList);
        Mockito.when(mapper.toListDTO(userList)).thenReturn(userListDTO);

        final var retorno = sut.findAll();

        Assertions.assertEquals(userListDTO.size(), retorno.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Mockito.verify(mapper, Mockito.times(1)).toListDTO(userList);
    }

    @Test
    @DisplayName("Deve lançar PlayerException quando houver erro ao procurar todas as questões por paginas")
    void deveLancarPlayerExceptionErroAoListarTodosPlayer() {
        List<Player> userList = new ArrayList<>(Collections.emptyList());
        Mockito.when(repository.findAll()).thenReturn(userList);

        final var excecao = Assertions.assertThrows(PlayerNotFoundException.class, () ->
                sut.findAll());

        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar um player ao procurar por id")
    void deveRetornarPlayerAoProcurarPorId() {
        final var id = 1L;
        final var playerEntity = new Player();
        playerEntity.setId(id);

        final var playerDTO = new PlayerDTO();
        playerDTO.setId(id);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(playerEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(playerEntity)).thenReturn(playerDTO);

        final var retorno = sut.findById(id);

        Assertions.assertEquals(playerEntity.getId(), retorno.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(playerEntity);
    }


    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por player por id")
    void deveLancarIllegalArgumentExceptionErroAoProcurarPlayerPorId() {
        final Long id = null;
        Mockito.when(repository.findById(id)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findById(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }


    @Test
    @DisplayName("Deve lançar PlayerException quando houver erro ao procurar por usuario por id")
    void deveLancarPlayerExceptionErroAoProcurarPlayerPorId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(PlayerNotFoundException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar player atualizado")
    void deveRetornarPlayerAtualizado() {
        final var playerDTO = PlayerFactory.createValidPlayerDTO();
        final var playerEntity = PlayerFactory.createValidPlayer();
        final var userEntity = UserFactory.createUser(1);

        Mockito.when(repository.findById(playerDTO.getId())).thenReturn(Optional.of(playerEntity));
        Mockito.when(repository.saveAndFlush(playerEntity)).thenReturn(playerEntity);
        Mockito.when(userMapper.toEntity(playerDTO.getUser())).thenReturn(userEntity);
        Mockito.when(mapper.toDTO(playerEntity)).thenReturn(playerDTO);

        PlayerDTO updatedPlayerDTO = sut.updatePlayer(playerDTO);

        assertEquals(playerDTO, updatedPlayerDTO);
        Mockito.verify(repository, Mockito.times(1)).saveAndFlush(playerEntity);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(playerEntity);
    }

    @Test
    @DisplayName("Deve lançar PlayerException ao atualizar player sem id")
    void deveLancarPlayerExceptionAoTentarAtualizarPlayerSemId() {
        final var playerDTO = PlayerFactory.createValidPlayerDTO();
        Mockito.when(repository.findById(null)).thenThrow(new PlayerException("Erro ao atualizar o player."));

        final var excecao = Assertions.assertThrows(PlayerException.class, () ->
                sut.updatePlayer(playerDTO));

        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar player salvo")
    void deveRetornarSalvarPlayer() {
        final var playerDTO = PlayerFactory.createValidPlayerDTO();
        final var playerEntity = PlayerFactory.createValidPlayer();
        Mockito.when(mapper.toEntity(playerDTO)).thenReturn(playerEntity);
        Mockito.when(repository.save(Mockito.any(Player.class))).thenReturn(playerEntity);
        Mockito.when(mapper.toDTO(playerEntity)).thenReturn(playerDTO);

        final var questionCapture = ArgumentCaptor.forClass(Player.class);
        sut.save(playerDTO);

        Mockito.verify(repository, Mockito.times(1)).save(questionCapture.capture());
        Assertions.assertEquals(playerDTO.getNickName(), questionCapture.getValue().getNickName());
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(Player.class));
    }


    @Test
    @DisplayName("Deve lançar PlayerException quando houver erro ao tentar salvar player")
    void deveLancarPlayerExceptionErroAoSalvarPlayer() {
        final var playerDTO = PlayerFactory.createValidPlayerDTO();
        final var playerEntity = PlayerFactory.createValidPlayer();
        Mockito.when(repository.save(Mockito.any(Player.class))).thenThrow(PlayerException.class);
        Mockito.when(mapper.toEntity(playerDTO)).thenReturn(playerEntity);

        final var excecao = Assertions.assertThrows(PlayerException.class, () ->
                sut.save(playerDTO));

        Mockito.verify(repository, Mockito.times(1)).save(playerEntity);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(playerDTO);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve deletar um player com sucesso")
    void deveDeletarUmPlayer() {
        final var id = 1L;
        final var playerEntity = new Player();
        playerEntity.setId(id);

        final var playerDTO = new PlayerDTO();
        playerDTO.setId(id);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(playerEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(playerEntity)).thenReturn(playerDTO);

        sut.deletePlayer(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar PlayerException quando houver erro ao tentar salvar deletar")
    void testDeletePlayerWithNullId() {
        final Long id = null;
        Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.deletePlayer(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PlayerException quando houver erro ao tentar salvar player que não existe")
    void testDeletePlayerWithNonExistingId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(PlayerNotFoundException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertNotNull(excecao);
    }

}