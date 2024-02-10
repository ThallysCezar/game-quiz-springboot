package com.mjv.gamequiz.dtos;

import com.mjv.gamequiz.domains.enums.UserRole;
import lombok.Data;

@Data
public class RegisterDTO {

    private String login;
    private String password;
    private UserRole role;
}
