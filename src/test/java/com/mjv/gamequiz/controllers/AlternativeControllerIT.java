package com.mjv.gamequiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import com.mjv.gamequiz.factories.AlternativeFactory;
import com.mjv.gamequiz.services.QuestionChoicesService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste AlternativeController")
public class AlternativeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionChoicesService alternativeService;


    @Test
    @DisplayName("Deve retornar uma alterantiva pelo seu id")
    void findById() throws Exception {
        QuestionChoicesDTO alternativeDTO = AlternativeFactory.createAlternativeDTO(1);

        when(alternativeService.findById(1L)).thenReturn(alternativeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/questionsAlternatives/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(alternativeDTO.getId().intValue())))
                .andExpect(jsonPath("$.alternative", is(alternativeDTO.getAlternative())))
                .andExpect(jsonPath("$.content", is(alternativeDTO.getContent())));
    }

    @Test
    @DisplayName("Deve retornar todas as alternativas")
    void testFindAll() throws Exception {
        Page<QuestionChoicesDTO> alternativesPage = new PageImpl<>(AlternativeFactory.createAlternativeDTOList(2));

        when(alternativeService.findAll(Mockito.any(Pageable.class))).thenReturn(alternativesPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/questionsAlternatives"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(alternativesPage.getContent().get(0).getId().intValue())))
                .andExpect(jsonPath("$.content[0].alternative", is(alternativesPage.getContent().get(0).getAlternative())))
                .andExpect(jsonPath("$.content[0].content", is(alternativesPage.getContent().get(0).getContent())))
                .andExpect(jsonPath("$.content[1].id", is(alternativesPage.getContent().get(1).getId().intValue())))
                .andExpect(jsonPath("$.content[1].alternative", is(alternativesPage.getContent().get(1).getAlternative())))
                .andExpect(jsonPath("$.content[1].content", is(alternativesPage.getContent().get(1).getContent())));
    }

    @Test
    @DisplayName("Deve retornar uma alternativa pelo id da questão")
    void testFindQuestionAlternativesByQuestionId() throws Exception {
        List<QuestionChoicesDTO> alternatives = AlternativeFactory.createAlternativeDTOList(2);

        when(alternativeService.findByQuestionId(1L)).thenReturn(alternatives);

        mockMvc.perform(MockMvcRequestBuilders.get("/questionsAlternatives/question-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(alternatives.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].alternative", is(alternatives.get(0).getAlternative())))
                .andExpect(jsonPath("$[0].content", is(alternatives.get(0).getContent())))
                .andExpect(jsonPath("$[1].id", is(alternatives.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].alternative", is(alternatives.get(1).getAlternative())))
                .andExpect(jsonPath("$[1].content", is(alternatives.get(1).getContent())));
    }

    @Test
    @DisplayName("Deve retornar uma alternativa salva")
    void testCountAlternativesByTheme() throws Exception {
        List<Object[]> counts = Arrays.asList(new Object[]{1L, 2L}, new Object[]{3L, 4L});

        when(alternativeService.countAlternativesByTheme()).thenReturn(counts);

        mockMvc.perform(MockMvcRequestBuilders.get("/questionsAlternatives/count-by-theme"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0][0]").value(1))
                .andExpect(jsonPath("$[0][1]").value(2))
                .andExpect(jsonPath("$[1][0]").value(3))
                .andExpect(jsonPath("$[1][1]").value(4));
    }

    @Test
    @DisplayName("Deve retornar uma lista de alternativas salvas")
    void testSaveAll() throws Exception {
        List<QuestionChoicesDTO> alternativeDTOs = AlternativeFactory.createAlternativeDTOList(1);


        when(alternativeService.saveAll(Mockito.anyList())).thenReturn(alternativeDTOs);

        mockMvc.perform(MockMvcRequestBuilders.post("/questionsAlternatives/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(alternativeDTOs)))
                .andExpect(status().isCreated());

        Mockito.verify(alternativeService, Mockito.times(1)).saveAll(Mockito.anyList());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}