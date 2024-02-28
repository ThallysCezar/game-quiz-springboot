package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionChoicesMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesException;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesNotFoundException;
import com.mjv.gamequiz.factories.AlternativeFactory;
import com.mjv.gamequiz.repositories.QuestionChoicesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

@DisplayName("Service de QuestionChoices")
public class QuestionChoicesServiceTest {

    private QuestionChoicesRepository repository;
    private QuestionChoicesService sut;
    private QuestionChoicesMapper mapper;


    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(QuestionChoicesRepository.class);
        this.mapper = Mockito.mock(QuestionChoicesMapper.class);
        this.sut = new QuestionChoicesService(repository, mapper);
    }

    @Test
    @DisplayName("Deve retornar todas as alternativas por paginas")
    void deveRetornarTodasAlternativas() {
        final var pageable = Pageable.unpaged();
        List<QuestionChoices> alternativesList = Arrays.asList(new QuestionChoices(), new QuestionChoices());
        Page<QuestionChoices> alternativesPage = new PageImpl<>(alternativesList, pageable, alternativesList.size());

        List<QuestionChoicesDTO> alternativesListDTO = Arrays.asList(new QuestionChoicesDTO(), new QuestionChoicesDTO());
        Page<QuestionChoicesDTO> alternativesPageDTO = new PageImpl<>(alternativesListDTO, pageable, alternativesListDTO.size());

        Mockito.when(repository.findAll(pageable)).thenReturn(alternativesPage);
        Mockito.when(mapper.toPageDTO(alternativesPage)).thenReturn(alternativesPageDTO);

        final var retorno = sut.findAll(pageable);

        Assertions.assertEquals(alternativesListDTO, retorno.getContent());
        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(mapper, Mockito.times(1)).toPageDTO(alternativesPage);
    }


    @Test
    @DisplayName("Deve lançar QuestionChoicesNotFoundException quando houver erro ao procurar todas as alternativas por paginas")
    void deveLancarQuestionChoicesNotFoundExceptionErroAoListarTodasAlternativasComPaginas() {
        final var pageable = Pageable.unpaged();
        Page<QuestionChoices> alternativesList = new PageImpl<>(Collections.emptyList());
        Mockito.when(repository.findAll(pageable)).thenReturn(alternativesList);

        final var excecao = Assertions.assertThrows(QuestionChoicesNotFoundException.class, () ->
                sut.findAll(pageable));

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar uma alternativa ao procurar por id")
    void deveRetornarAlternativaAoProcurarPorId() {
        final var id = 1L;
        final var alternativeEntity = new QuestionChoices();
        alternativeEntity.setId(id);

        final var alternativeDTO = new QuestionChoicesDTO();
        alternativeDTO.setId(id);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(alternativeEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(alternativeEntity)).thenReturn(alternativeDTO);

        final var retorno = sut.findById(id);

        Assertions.assertEquals(alternativeEntity.getId(), retorno.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(alternativeEntity);
    }


    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por alternativa por id")
    void deveLancarIllegalArgumentExceptionErroAoProcurarUsuarioPorId() {
        final Long id = null;
        Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findById(id));

        Assertions.assertEquals("O ID não pode ser nulo, tente novamente.", excecao.getMessage());
    }


    @Test
    @DisplayName("Deve lançar AlternativeException quando houver erro ao procurar por alterantiva por id")
    void deveLancarAlternativeExceptionErroAoProcurarAlternativaPorId() {
        final Long id = 9999L;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        final var excecao = Assertions.assertThrows(QuestionChoicesNotFoundException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar uma alterantiva ao procurar por alterantiva por id da questão")
    void deveRetornarAlternativaAoProcurarPorIdQuestao() {
        final var id = 1L;
        final var question = new Question();
        question.setId(id);
        final var alternativeEntity = new QuestionChoices();
        alternativeEntity.setQuestion(question);

        final var alternativeDTO = new QuestionChoicesDTO();

        Mockito.when(repository.findByQuestionId(Mockito.anyLong())).thenReturn(List.of(alternativeEntity));
        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(mapper.toDTO(alternativeEntity)).thenReturn(alternativeDTO);

        final var retorno = sut.findByQuestionId(id);

        Assertions.assertEquals(alternativeEntity.getId(), retorno.get(0).getContent());
        Mockito.verify(repository, Mockito.times(1)).findByQuestionId(id);
        Mockito.verify(mapper, Mockito.times(1)).toDTO(alternativeEntity);
    }


    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando houver erro ao procurar por alterantiva por id da questão")
    void deveLancarIllegalArgumentExceptionErroAoProcurarAlternativaPorIdQuestao() {
        final Long id = null;
        Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException("O ID não pode ser nulo, tente novamente."));

        final var excecao = Assertions.assertThrows(IllegalArgumentException.class, () ->
                sut.findByQuestionId(id));

        Assertions.assertEquals("O ID da questão não pode ser nulo, tente novamente.", excecao.getMessage());
    }


    @Test
    @DisplayName("Deve retornar alternativa salva")
    void deveRetornarSalvarAlternativa() {
        final var alternativeDTO = AlternativeFactory.createValidAlternativeDTO();
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(mapper.toDTO(alternativeEntity)).thenReturn(alternativeDTO);
        Mockito.when(repository.save(Mockito.any(QuestionChoices.class))).thenReturn(alternativeEntity);
        Mockito.when(mapper.toEntity(alternativeDTO)).thenReturn(alternativeEntity);

        final var alternativeCapture = ArgumentCaptor.forClass(QuestionChoices.class);
        sut.save(alternativeDTO);

        Mockito.verify(repository, Mockito.times(1)).save(alternativeCapture.capture());
        Assertions.assertEquals(alternativeDTO.getAlternative(), alternativeCapture.getValue().getAlternative());
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(QuestionChoices.class));
        Mockito.verify(mapper, Mockito.times(1)).toEntity(Mockito.any(QuestionChoicesDTO.class));
    }


    @Test
    @DisplayName("Deve lançar QuestionChoicesNotFoundException quando houver erro ao tentar salvar uma questão")
    void deveLancarQuestionChoicesNotFoundExceptionErroAoSalvarAlternativas() {
        final var alternativeDTO = AlternativeFactory.createValidAlternativeDTO();
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(repository.save(Mockito.any(QuestionChoices.class))).thenThrow(QuestionChoicesException.class);
        Mockito.when(mapper.toEntity(alternativeDTO)).thenReturn(alternativeEntity);

        final var excecao = Assertions.assertThrows(QuestionChoicesException.class, () ->
                sut.save(alternativeDTO));

        Mockito.verify(repository, Mockito.times(1)).save(alternativeEntity);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(alternativeDTO);
        Assertions.assertNotNull(excecao);
    }

    @Test
    @DisplayName("Deve retornar uma lista de alternativas salvas")
    void deveRetornarSalvarListaAlternativas() {
        final var alternativeDTO = AlternativeFactory.createValidAlternativeDTO();
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(mapper.toDTO(alternativeEntity)).thenReturn(alternativeDTO);
        Mockito.when(repository.saveAll(Mockito.anyList())).thenReturn(List.of(alternativeEntity));

        sut.saveAll(List.of(alternativeEntity));

        Mockito.verify(repository, Mockito.times(1)).saveAll(Mockito.anyList());
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(QuestionChoices.class));
    }


    @Test
    @DisplayName("Deve lançar AlternativeException quando houver erro ao tentar salvar uma lista de alternativas")
    void deveLancarAlternativeExceptionErroAoSalvarUmaListaDeAlternativas() {
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(repository.saveAll(Mockito.anyList())).thenThrow(QuestionChoicesException.class);

        Assertions.assertThrows(QuestionChoicesException.class, () -> sut.saveAll(List.of(alternativeEntity)));

        Mockito.verify(repository, Mockito.times(1)).saveAll(List.of(alternativeEntity));
    }

    @Test
    @DisplayName("Deve retornar lista de contagem de alternativas por temas")
    void deveRetornarListaDeContagemDeQuestoesPorTema() {
        List<Object[]> listagem = new ArrayList<>();
        Mockito.when(repository.countAlternativesByTheme()).thenReturn(listagem);

        List<Object[]> retorno = sut.countAlternativesByTheme();

        Assertions.assertEquals(listagem, retorno);
    }
}
