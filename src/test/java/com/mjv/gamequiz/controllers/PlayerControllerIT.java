package com.mjv.gamequiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.factories.PlayerFactory;
import com.mjv.gamequiz.services.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste PlayerController")
public class PlayerControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PlayerService playerService;

    @Test
    @DisplayName("Deve retornar um player ao procurar por id")
    public void testFindPlayerById() throws Exception {
        PlayerDTO playerDTO = PlayerFactory.createValidPlayerDTO(1);


        when(playerService.findById(1L)).thenReturn(playerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/1", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Deve retornar todos os players")
    public void testFindAllPlayers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Adicione mais validações conforme necessário
    }

    @Test
    @DisplayName("Deve retornar um player atualizado")
    public void testUpdatePlayer() throws Exception {
        PlayerDTO playerDTO = PlayerFactory.createValidPlayerDTO(1);

        when(playerService.updatePlayer(Mockito.any(PlayerDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));


        mockMvc.perform(MockMvcRequestBuilders.put("/players/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um player salvo")
    public void testSavePlayer() throws Exception {
        PlayerDTO playerDTO = PlayerFactory.createValidPlayerDTO(1);

        when(playerService.save(Mockito.any(PlayerDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deve deletar um player")
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