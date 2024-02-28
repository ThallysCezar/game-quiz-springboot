package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.domains.enums.UserRole;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.dtos.UserDTO;

public class PlayerFactory {

    public static PlayerDTO createValidPlayerDTO(int index) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId((long) index);
        playerDTO.setNickName("Player1");

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("player1@example.com");

        playerDTO.setUser(userDTO);
        playerDTO.setScore(1000);
        return playerDTO;
    }

    public static PlayerDTO createValidPlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(1L);
        playerDTO.setNickName("Sample Nickname");
        playerDTO.setScore(100);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setLogin("player1@example.com");
        userDTO.setRole(UserRole.ADMIN);
        userDTO.setPassword("password");

        playerDTO.setUser(userDTO);

        return playerDTO;
    }

    public static Player createValidPlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setNickName("Sample Nickname");
        player.setScore(100);

        User user = new User();
        user.setId(1L);
        user.setLogin("sample_username");
        user.setRole(UserRole.ADMIN);
        user.setPassword("password");

        player.setUser(user);

        return player;
    }

}
