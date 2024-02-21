package com.mjv.gamequiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.factories.PlayerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste PlayerController")
class PlayerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindPlayerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/players/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void testFindAllPlayers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Adicione mais validações conforme necessário
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        PlayerDTO playerDTO = PlayerFactory.createValidPlayerDTO();
        mockMvc.perform(MockMvcRequestBuilders.put("/players/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testSavePlayer() throws Exception {
        PlayerDTO playerDTO = PlayerFactory.createValidPlayerDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeletePlayer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/players/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}