package com.mjv.gamequiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.QuizGameDTO;
import com.mjv.gamequiz.dtos.ThemeDTO;
import com.mjv.gamequiz.factories.QuestionFactory;
import com.mjv.gamequiz.factories.QuizFactory;
import com.mjv.gamequiz.services.QuestionService;
import com.mjv.gamequiz.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste QuizController")
public class QuizControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Deve retornar questão por usuário e tema")
    public void testFindQuestionByUserAndTheme() throws Exception {
        QuestionDTO questionDTO = QuestionFactory.createValidQuestionDTO();
        final var questionWithOutResponseDTO = QuestionFactory.createValidQuestionWithoutResponseDTO();
        ThemeDTO themeDTO = new ThemeDTO();
        themeDTO.setId(1L);
        questionDTO.setTheme(themeDTO);
        String login = "thallys@hotmail.com";
        String theme = "Cinema";

        when(userService.userExists(login)).thenReturn(true);
        when(questionService.getRandomQuestionByTheme(theme)).thenReturn(questionWithOutResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/game-quiz/loginByUser/%s/theme/%s", login, theme))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theme.id").value(questionDTO.getTheme().getId().intValue()))
                .andExpect(jsonPath("$.answer").value(questionDTO.getAnswer()));
    }

    @Test
    @DisplayName("Deve retornar um game-quiz")
    public void testSubmitAnswer() throws Exception {
        QuizGameDTO quizGameDTO = QuizFactory.createQuizGameDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/game-quiz/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(quizGameDTO)))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}