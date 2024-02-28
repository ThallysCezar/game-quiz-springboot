package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.PlayerMapper;
import com.mjv.gamequiz.builders.UserMapper;
import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.exceptions.Player.PlayerException;
import com.mjv.gamequiz.exceptions.Player.PlayerNotFoundException;
import com.mjv.gamequiz.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final UserMapper userMapper;

    public List<PlayerDTO> findAll() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("Nenhum player encontrado");
        }

        return playerMapper.toListDTO(players);
    }

    public PlayerDTO findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException(String.format("Player não encontrado com o id '%s'.", id));
        }

        return playerRepository.findById(id)
                .map(playerMapper::toDTO)
                .orElseThrow(() -> new PlayerException("Erro ao tentar procurar um player"));
    }

    public PlayerDTO updatePlayer(PlayerDTO playerDTO) {
        try {
            Player existingPlayer = playerRepository.findById(playerDTO.getId())
                    .orElseThrow(() -> new PlayerNotFoundException("Jogador não encontrado com ID: " + playerDTO.getId()));

            User user = userMapper.toEntity(playerDTO.getUser());
            existingPlayer.setUser(user);
            existingPlayer.setNickName(playerDTO.getNickName());
            return playerMapper.toDTO(playerRepository.saveAndFlush(existingPlayer));
        } catch (Exception exUser) {
            throw new PlayerException("Erro ao atualizar o player.");
        }
    }

    public PlayerDTO save(PlayerDTO playerDTO) {
        try {
            return playerMapper.toDTO(playerRepository.save(playerMapper.toEntity(playerDTO)));
        } catch (Exception exPlayer) {
            throw new PlayerException("Erro ao salvar um player.");
        }
    }

    public void deletePlayer(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("O ID não pode ser nulo, tente novamente.");
        }
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException(String.format("Player não encontrado com o id '%s'.", id));
        }
        playerRepository.deleteById(id);
    }

}