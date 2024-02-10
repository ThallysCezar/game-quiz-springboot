package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.exceptions.QuestionException;
import com.mjv.gamequiz.factories.QuestionFactory;
import com.mjv.gamequiz.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;

@DisplayName("Service de Question")
public class QuestionServiceTest {

    private QuestionRepository repository;
    private QuestionService sut;
    private QuestionMapper mapper;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(QuestionRepository.class);
        this.mapper = Mockito.mock(QuestionMapper.class);
        this.sut = new QuestionService(repository, mapper);
    }

    //cenario feliz findAll
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

    //cenario triste findAll
    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao procurar todas as questões por paginas")
    void deveLancarQuestionExceptionErroAoTodosComPaginas() {
        final var pageable = Pageable.unpaged();
        Page<Question> questionList = new PageImpl<>(Collections.emptyList());
        Mockito.when(repository.findAll(pageable)).thenReturn(questionList);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.findAll(pageable));

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Assertions.assertEquals("Nenhuma pergunta encontrada.", excecao.getMessage());
    }

    //cenario feliz findAllQuestionsWithAlternatives
    @Test
    @DisplayName("Deve retornar todas as questões com suas respectivas alternativas")
    void deveRetornarTodasQuestoesComAlternativas() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestion("Texto da questão 1");

        List<QuestionAlternative> alternatives1 = Arrays.asList(new QuestionAlternative(), new QuestionAlternative());
        question1.setQuestionAlternativeList(alternatives1);

        List<Question> questions = Arrays.asList(question1);

        Mockito.when(repository.findAllQuestionsWithAlternatives()).thenReturn(questions);
        Mockito.when(mapper.toListDTO(questions)).thenReturn(Arrays.asList(new QuestionDTO()));

        final var retorno = sut.findAllQuestionsWithAlternatives();

        Assertions.assertEquals(questions.size(), retorno.size());
        Mockito.verify(repository, Mockito.times(1)).findAllQuestionsWithAlternatives();
        Mockito.verify(mapper, Mockito.times(1)).toListDTO(questions);
    }

    //cenario triste findAllQuestionsWithAlternatives
    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao logar um usuário com o username")
    void deveLancarQuestionExceptionErroAoBuscarTodasQuestoesComAlternativas() {
        List<Question> questions = new ArrayList<>(Collections.emptyList());
        Mockito.when(repository.findAll()).thenReturn(questions);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.findAllQuestionsWithAlternatives());

        Mockito.verify(repository, Mockito.times(1)).findAllQuestionsWithAlternatives();
        Assertions.assertEquals("Nenhuma pergunta encontrada.", excecao.getMessage());
    }

    //cenario feliz findById
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

    //cenario triste findById
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por usuario por id")
    void deveLancarIllegalArgumentExceptionErroAoProcurarUsuarioPorId() {
        final Long id = null;
        Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findById(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }

    //cenario triste findById
    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao procurar por usuario por id")
    void deveLancarQuestionExceptionErroAoProcurarUsuarioPorId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertEquals(String.format("Pergunta não encontrado com o id '%s'.", id), excecao.getMessage());
    }

    //cenario feliz save
    @Test
    @DisplayName("Deve retornar questões salvas")
    void deveRetornarSalvarQuestoes() {
        final var questionDTO = QuestionFactory.createValidQuestionDTO();
        final var questionEntity = QuestionFactory.createValidQuestion();
        Mockito.when(repository.save(Mockito.any(Question.class))).thenReturn(questionEntity);
        Mockito.when(mapper.toDTO(Mockito.any(Question.class))).thenReturn(questionDTO);

        final var retorno = sut.save(questionDTO);

        Assertions.assertEquals(questionEntity.getQuestionAlternativeList().size(),
                retorno.getQuestionAlternativeDTOList().size());
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Question.class));
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(Question.class));
    }

//    @Test
//    @DisplayName("Deve cadastrar um dispositivo")
//    void deveCadastrarUmDispositivo() {
//        final var ec = "ec";
//        final var numeroLogico = "numeroLogico";
//        final var dispositivo = new DispositivoVendedorLoja();
//        dispositivo.setEc(ec);
//        dispositivo.setNumeroLogico(numeroLogico);
//        Mockito.when(repository.findByEcAndNumeroLogicoAndAtivoTrue(ec, numeroLogico))
//                .thenReturn(Optional.empty());
//
//        sut.cadastrarDispositivo(dispositivo);
//
//        Mockito.verify(repository, Mockito.times(1))
//                .findByEcAndNumeroLogicoAndAtivoTrue(ec, numeroLogico);
//        Mockito.verify(repository, Mockito.times(1)).save(dispositivo);
//    }

    //cenario triste save
    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao tentar salvar uma questão")
    void deveLancarQuestionExceptionErroAoSalvarQuestoes() {
        final var questionDTO = new QuestionDTO();
        final var questionEntity = new Question();
        Mockito.when(repository.save(questionEntity)).thenThrow(QuestionException.class);
        Mockito.when(mapper.toDTO(questionEntity)).thenReturn(questionDTO);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.save(questionDTO));

        Mockito.verify(repository, Mockito.times(1)).save(questionEntity);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(questionEntity);
        Assertions.assertEquals("Erro ao tentar salvar a pergunta.", excecao.getMessage());
    }

    //cenario feliz getQuestionsByTheme
    @Test
    @DisplayName("Deve retornar tema das questões")
    void deveRetornarQuestoesPorTemas() {
        final var theme = "Cinema";
        List<Question> questions = new ArrayList<>();
        Mockito.when(repository.findByTheme(theme)).thenReturn(questions);

        final var retorno = sut.getQuestionsByTheme(theme);

        Assertions.assertEquals(questions, retorno);
        Mockito.verify(repository, Mockito.times(1)).findByTheme(theme);
    }

    //cenario triste getQuestionsByTheme
    @Test
    @DisplayName("Deve lançar QuestionException quando houver erro ao procurar tema das questões")
    void deveLancarQuestionExceptionErroAoProcurarTemaQuestao() {
        final var theme = "testUser";
        List<Question> questions = new ArrayList<>();
        Mockito.when(repository.findByTheme(theme)).thenThrow(QuestionException.class);

        final var excecao = Assertions.assertThrows(QuestionException.class, () ->
                sut.getQuestionsByTheme(theme));

        Mockito.verify(repository, Mockito.times(1)).findByTheme(theme);
        Assertions.assertEquals("Erro ao procurar por tema de questões.", excecao.getMessage());
    }

}
