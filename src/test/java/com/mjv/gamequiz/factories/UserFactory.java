package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.domains.enums.UserRole;
import com.mjv.gamequiz.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static List<UserDTO> createUserDTOs(int size) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            userDTOs.add(createUserDTO(i));
        }
        return userDTOs;
    }

    public static UserDTO createUserDTO(int index) {
        return new UserDTO((long) index, "user" + index, "password" + index, UserRole.USER);
    }

    public static User createUser(int index) {
        User user = new User();
        user.setId((long) index);
        user.setLogin("user" + index);
        user.setPassword("password" + index);
        user.setRole(UserRole.USER);
        return user;
    }

}
