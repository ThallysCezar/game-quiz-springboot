package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.AlternativeDTO;
import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.factories.AlternativeFactory;
import com.mjv.gamequiz.factories.UserFactory;
import com.mjv.gamequiz.services.AlternativeService;
import com.mjv.gamequiz.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste UserController")
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Deve retornar um usuário pelo seu id")
    void findById() throws Exception {
        UserDTO userDTO = UserFactory.createUserDTO(1);

        when(userService.findUserById(1L)).thenReturn(userDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDTO.getId().intValue())))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())));
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void testFindAll() throws Exception {
        List<UserDTO> userDTOList = UserFactory.createUserDTOs(2);

        when(userService.findAll()).thenReturn(userDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(userDTOList.get(0).getId()))
                .andExpect(jsonPath("$[0].login").value(userDTOList.get(0).getLogin()))
                .andExpect(jsonPath("$[0].password").value(userDTOList.get(0).getPassword()))
                .andExpect(jsonPath("$[0].role").value(userDTOList.get(0).getRole().toString()))
                .andExpect(jsonPath("$[1].id").value(userDTOList.get(1).getId()))
                .andExpect(jsonPath("$[1].login").value(userDTOList.get(1).getLogin()))
                .andExpect(jsonPath("$[1].password").value(userDTOList.get(1).getPassword()))
                .andExpect(jsonPath("$[1].role").value(userDTOList.get(1).getRole().toString()));
    }

}