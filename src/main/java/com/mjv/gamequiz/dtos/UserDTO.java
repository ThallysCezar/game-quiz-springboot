package com.mjv.gamequiz.dtos;

import com.mjv.gamequiz.domains.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private UserRole role;

    public UserDTO(Long id, String username) {
    }

    @Override
    public String toString() {
        return "\n\nUser:" +
                "\nlogin= " + login +
                "\npassword= " + password +
                "\nrole= " + role;
    }
}
