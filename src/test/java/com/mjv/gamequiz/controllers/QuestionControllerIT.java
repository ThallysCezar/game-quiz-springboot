package com.mjv.gamequiz.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.factories.QuestionFactory;
import com.mjv.gamequiz.services.QuestionService;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste QuestionController")
public class QuestionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    @DisplayName("Deve retornar todas as questões")
    void testFindAllQuestions() throws Exception {
        List<QuestionDTO> questions = QuestionFactory.createQuestionDTOList(2);

        when(questionService.findAllQuestionsWithAlternatives()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/question"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar uma questão por id")
    void testFindById() throws Exception {
        QuestionDTO question = QuestionFactory.createQuestionDTO(1);

        when(questionService.findById(1L)).thenReturn(question);

        mockMvc.perform(MockMvcRequestBuilders.get("/question/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(question.getId().intValue())))
                .andExpect(jsonPath("$.theme.theme", is(question.getTheme().getTheme())))
                .andExpect(jsonPath("$.alternativeDTOList", hasSize(question.getAlternativeDTOList().size())))
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar uma busca de questão por tema")
    void testGetQuestionsByThemeName() throws Exception {
        List<QuestionDTO> questions = QuestionFactory.createQuestionDTOList(2);

        when(questionService.getQuestionsByTheme("theme")).thenReturn(questions);
        mockMvc.perform(MockMvcRequestBuilders.get("/question/theme/theme"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar uma questão salva")
    void testSave() throws Exception {
        QuestionDTO question = QuestionFactory.createQuestionDTO(1);

        when(questionService.save(Mockito.any(QuestionDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(question)))
                .andExpect(status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
