package com.mjv.gamequiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.config.security.TokenService;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.domains.enums.UserRole;
import com.mjv.gamequiz.dtos.AuthenticationDTO;
import com.mjv.gamequiz.dtos.RegisterDTO;
import com.mjv.gamequiz.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste AuthenticationController")
public class AuthenticationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLogin() throws Exception {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setLogin("username");
        authenticationDTO.setPassword("password");

        String token = "generated-token";
        User user = new User("username", "password", UserRole.USER); // Criar um usuário válido

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);

        when(authenticationManager.authenticate(Mockito.any())).thenReturn(usernamePasswordAuthenticationToken);
        when(tokenService.generateToken(Mockito.any(User.class))).thenReturn(token);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authenticationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
    }

    @Test
    public void testRegister() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setLogin("newUser");
        registerDTO.setPassword("newPassword");
        registerDTO.setRole(UserRole.USER);

        when(userRepository.findByLogin(Mockito.anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO)))
                .andExpect(status().isOk());

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

}