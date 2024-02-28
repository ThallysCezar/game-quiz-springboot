package com.mjv.gamequiz.dtos;

import com.mjv.gamequiz.domains.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private UserRole role;

}