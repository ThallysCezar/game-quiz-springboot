package com.mjv.gamequiz.services;

import com.mjv.gamequiz.mappers.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.ThemeDTO;
import com.mjv.gamequiz.exceptions.Question.QuestionException;
import com.mjv.gamequiz.exceptions.Question.QuestionNotFoundException;
import com.mjv.gamequiz.factories.QuestionFactory;
import com.mjv.gamequiz.repositories.QuestionRepository;
import com.mjv.gamequiz.repositories.ThemeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

@DisplayName("Service de Question")
public class QuestionServiceTest {

    private QuestionRepository repository;
    private QuestionService sut;
    private QuestionMapper mapper;
    private ThemeRepository themeRepository;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(QuestionRepository.class);
        this.themeRepository = Mockito.mock(ThemeRepository.class);
        this.mapper = Mockito.mock(QuestionMapper.class);
        this.sut = new QuestionService(repository, mapper, themeRepository);
    }


    @Test
    @DisplayName("Deve retornar todas as questões")
    void deveRetornarTodasQuestoes() {
        final var pageable = Pageable.unpaged();
        List<Question> questionsList = Arrays.asList(new Question(), new Question());
        Page<Question> questionsPage = new PageImpl<>(questionsList, pageable, questionsList.size());

        List<QuestionDTO> questionsListDTO = Arrays.asList(new QuestionDTO(), new QuestionDTO());
        Page<QuestionDTO> questionsPageDTO = new PageImpl<>(questionsListDTO, pageable, questionsListDTO.size());

        Mockito.when(repository.findAll(pageable)).thenReturn(questionsPage);
        Mockito.when(mapper.toPageDTO(questionsPage)).thenReturn(questionsPageDTO);

        final var retorno = sut.findAll(pageable);

        Assertions.assertEquals(questionsListDTO, retorno.getContent());
        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(mapper, Mockito.times(1)).toPageDTO(questionsPage);
    }


    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao procurar todas as questões por paginas")
    void deveLancarQuestionExceptionErroAoTodosComPaginas() {
        final var pageable = Pageable.unpaged();
        Page<Question> questionList = new PageImpl<>(Collections.emptyList());
        Mockito.when(repository.findAll(pageable)).thenReturn(questionList);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.findAll(pageable));

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Assertions.assertNotNull(excecao);
    }


    @Test
    @DisplayName("Deve retornar todas as questões com suas respectivas alternativas")
    void deveRetornarTodasQuestoesComAlternativas() {
        final var pageable = Pageable.unpaged();
        Question question1 = new Question();
        question1.setId(1L);
        question1.setAnswer("Texto da questão 1");

        List<QuestionChoices> alternatives1 = Arrays.asList(new QuestionChoices(), new QuestionChoices());
        question1.setAlternativeList(alternatives1);

        List<Question> questions = Arrays.asList(question1);
        Page<Question> questionList = new PageImpl<>(questions);

        Mockito.when(repository.findAll(pageable)).thenReturn(questionList);
        Mockito.when(mapper.toListDTO(questions)).thenReturn(Arrays.asList(new QuestionDTO()));

        final var retorno = sut.findAll(pageable);

        Assertions.assertEquals(questions.size(), retorno.getTotalElements());
        Mockito.verify(repository, Mockito.times(1)).findAllQuestionsWithAlternatives();
        Mockito.verify(mapper, Mockito.times(1)).toListDTO(questions);
    }


    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao logar um usuário com o username")
    void deveLancarQuestionExceptionErroAoBuscarTodasQuestoesComAlternativas() {
        final var pageable = Pageable.unpaged();
        Page<Question> questions = Page.empty();
        Mockito.when(repository.findAll(pageable)).thenReturn(questions);

        final var excecao = Assertions.assertThrows(QuestionNotFoundException.class, () ->
                sut.findAll(pageable));

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Assertions.assertNotNull(excecao);
    }


    @Test
    @DisplayName("Deve retornar uma questão ao procurar por id")
    void deveRetornarQuestaoAoProcurarPorId() {
        final var id = 1L;
        final var questionEntity = new Question();
        questionEntity.setId(id);

        final var questionDTO = new QuestionDTO();
        questionDTO.setId(id);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(questionEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(questionEntity)).thenReturn(questionDTO);

        final var retorno = sut.findById(id);

        Assertions.assertEquals(questionEntity.getId(), retorno.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(questionEntity);
    }


    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por usuario por id")
    void deveLancarIllegalArgumentExceptionErroAoProcurarUsuarioPorId() {
        final Long id = null;
        Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findById(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }


    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao procurar por usuario por id")
    void deveLancarQuestionExceptionErroAoProcurarUsuarioPorId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(QuestionNotFoundException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertNotNull(excecao);
    }


    @Test
    @DisplayName("Deve retornar questões salvas")
    void deveRetornarSalvarQuestoes() {
        // Arrange
        final var questionDTO = QuestionFactory.createValidQuestionDTO();
        final var questionEntity = QuestionFactory.createValidQuestion();

        Mockito.when(mapper.toEntity(questionDTO)).thenReturn(questionEntity);
        Mockito.when(themeRepository.findByTheme(questionEntity.getTheme().getTheme())).thenReturn(Optional.of(new Theme()));
        Mockito.when(repository.save(Mockito.any(Question.class))).thenReturn(questionEntity);
        Mockito.when(mapper.toDTO(questionEntity)).thenReturn(questionDTO);

        // Act
        QuestionDTO savedQuestionDTO = sut.save(questionDTO);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(questionEntity);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(questionEntity);
        Assertions.assertEquals(questionDTO.getAnswer(), savedQuestionDTO.getAnswer());
    }

    @Test
    @DisplayName("Deve lançar exceção em caso de erro desconhecido")
    void deveLancarExcecaoErroDesconhecido() {
        final var questionDTO = QuestionFactory.createValidQuestionDTO();
        final var questionEntity = QuestionFactory.createValidQuestion();

        Mockito.when(mapper.toEntity(questionDTO)).thenReturn(questionEntity);
        Mockito.when(themeRepository.findByTheme(questionEntity.getTheme().getTheme())).thenThrow(new RuntimeException("Erro desconhecido"));

        final var excecao = Assertions.assertThrows(QuestionException.class, () -> sut.save(questionDTO));

        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar lista de questões por tema")
    void deveRetornarListaDeQuestaoPorTema() {
        String themeName = "Tema questão";
        final var questionDTO = QuestionFactory.createValidQuestionDTO();
        final var questionEntity = QuestionFactory.createValidQuestion();
        Mockito.when(repository.findByThemeName(themeName)).thenReturn(List.of(questionEntity));
        Mockito.when(mapper.toListDTO(List.of(questionEntity))).thenReturn(List.of(questionDTO));

        List<QuestionDTO> retorno = sut.getQuestionsByTheme(themeName);

        Assertions.assertEquals(List.of(questionEntity).size(), retorno.size());
        Mockito.verify(repository, Mockito.times(1)).findByThemeName(themeName);
        Mockito.verify(mapper, Mockito.times(1)).toListDTO(List.of(questionEntity));
    }

    @Test
    @DisplayName("Deve retornar uma questão randomica por tema")
    void deveRetornarUmaQuestaoRandomicaPorTema() {
        String themeName = "Cinema";
        final var questionDTO = QuestionFactory.createValidQuestionDTO();
        ThemeDTO theme = new ThemeDTO();
        theme.setTheme(themeName);
        final var questionEntity = QuestionFactory.createValidQuestion();
        questionDTO.setTheme(theme);

        Mockito.when(repository.findByThemeName(themeName)).thenReturn(List.of(questionEntity));
        Mockito.when(mapper.toDTO(questionEntity)).thenReturn(questionDTO);

        final var retorno = sut.getRandomQuestionByTheme(theme.getTheme());

        Assertions.assertEquals(themeName, retorno.getTheme().getTheme());
        Mockito.verify(repository, Mockito.times(1)).findByThemeName(themeName);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(questionEntity);
    }

    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao tentar buscar uma questão randomica por tema")
    void deveLancarQuestionExceptionAoBuscarUmaQuestoesRandomicaPorTema() {
        String themeName = "Tema questão";
        Mockito.when(repository.findByThemeName(themeName)).thenReturn(Collections.emptyList());

        final var excecao = Assertions.assertThrows(QuestionNotFoundException.class, () ->
                sut.getRandomQuestionByTheme(themeName));

        Mockito.verify(repository, Mockito.times(1)).findByThemeName(themeName);
        Assertions.assertNotNull(excecao);
    }

}