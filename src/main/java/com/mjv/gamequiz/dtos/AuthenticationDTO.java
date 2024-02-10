package com.mjv.gamequiz.dtos;

import lombok.Data;

@Data
public class AuthenticationDTO {

    private String login;
    private String password;

}