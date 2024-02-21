package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.dtos.UserDTO;

public class PlayerFactory {

    public static PlayerDTO createValidPlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(1L);
        playerDTO.setNickName("Player1");

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("player1@example.com");

        playerDTO.setUser(userDTO);
        playerDTO.setScore(1000);
        return playerDTO;
    }

}
