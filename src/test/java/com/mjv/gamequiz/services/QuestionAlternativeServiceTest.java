package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.AlternativeMapper;
import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.AlternativeDTO;
import com.mjv.gamequiz.exceptions.AlternativeException;
import com.mjv.gamequiz.factories.AlternativeFactory;
import com.mjv.gamequiz.repositories.AlternativeRepository;
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

@DisplayName("Service de Alternative")
public class QuestionAlternativeServiceTest {

    private AlternativeRepository repository;
    private AlternativeService sut;
    private AlternativeMapper mapper;


    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(AlternativeRepository.class);
        this.mapper = Mockito.mock(AlternativeMapper.class);
        this.sut = new AlternativeService(repository, mapper);
    }

    @Test
    @DisplayName("Deve retornar todas as alternativas por paginas")
    void deveRetornarTodasAlternativas() {
        final var pageable = Pageable.unpaged();
        List<Alternative> alternativesList = Arrays.asList(new Alternative(), new Alternative());
        Page<Alternative> alternativesPage = new PageImpl<>(alternativesList, pageable, alternativesList.size());

        List<AlternativeDTO> alternativesListDTO = Arrays.asList(new AlternativeDTO(), new AlternativeDTO());
        Page<AlternativeDTO> alternativesPageDTO = new PageImpl<>(alternativesListDTO, pageable, alternativesListDTO.size());

        Mockito.when(repository.findAll(pageable)).thenReturn(alternativesPage);
        Mockito.when(mapper.toPageDTO(alternativesPage)).thenReturn(alternativesPageDTO);

        final var retorno = sut.findAll(pageable);

        Assertions.assertEquals(alternativesListDTO, retorno.getContent());
        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(mapper, Mockito.times(1)).toPageDTO(alternativesPage);
    }


    @Test
    @DisplayName("Deve lançar AlternativeException quando houver erro ao procurar todas as alternativas por paginas")
    void deveLancarAlternativeExceptionErroAoListarTodasAlternativasComPaginas() {
        final var pageable = Pageable.unpaged();
        Page<Alternative> alternativesList = new PageImpl<>(Collections.emptyList());
        Mockito.when(repository.findAll(pageable)).thenReturn(alternativesList);

        final var excecao = Assertions.assertThrows(AlternativeException.class, () ->
                sut.findAll(pageable));

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
        Assertions.assertEquals("Nenhuma alternativa de pergunta encontrada.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma alternativa ao procurar por id")
    void deveRetornarAlternativaAoProcurarPorId() {
        final var id = 1L;
        final var alternativeEntity = new Alternative();
        alternativeEntity.setId(id);

        final var alternativeDTO = new AlternativeDTO();
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

        final var excecao = Assertions.assertThrows(AlternativeException.class, () ->
                sut.findById(id));

        Mockito.verify(repository, Mockito.times(1)).existsById(id);
        Assertions.assertEquals(String.format("Alternativa não encontrada com o id '%s'.", id), excecao.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma alterantiva ao procurar por alterantiva por id da questão")
    void deveRetornarAlternativaAoProcurarPorIdQuestao() {
        final var id = 1L;
        final var question = new Question();
        question.setId(id);
        final var alternativeEntity = new Alternative();
        alternativeEntity.setQuestion(question);

        final var alternativeDTO = new AlternativeDTO();

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
        Mockito.when(repository.save(Mockito.any(Alternative.class))).thenReturn(alternativeEntity);
        Mockito.when(mapper.toEntity(alternativeDTO)).thenReturn(alternativeEntity);

        final var alternativeCapture = ArgumentCaptor.forClass(Alternative.class);
        sut.save(alternativeDTO);

        Mockito.verify(repository, Mockito.times(1)).save(alternativeCapture.capture());
        Assertions.assertEquals(alternativeDTO.getAlternative(), alternativeCapture.getValue().getAlternative());
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(Alternative.class));
        Mockito.verify(mapper, Mockito.times(1)).toEntity(Mockito.any(AlternativeDTO.class));
    }


    @Test
    @DisplayName("Deve lançar AlternativeException quando houver erro ao tentar salvar uma questão")
    void deveLancarAlternativeExceptionErroAoSalvarAlternativas() {
        final var alternativeDTO = AlternativeFactory.createValidAlternativeDTO();
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(repository.save(Mockito.any(Alternative.class))).thenThrow(AlternativeException.class);
        Mockito.when(mapper.toEntity(alternativeDTO)).thenReturn(alternativeEntity);

        final var excecao = Assertions.assertThrows(AlternativeException.class, () ->
                sut.save(alternativeDTO));

        Mockito.verify(repository, Mockito.times(1)).save(alternativeEntity);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(alternativeDTO);
        Assertions.assertEquals("Erro ao tentar salvar a alternativa", excecao.getMessage());
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
        Mockito.verify(mapper, Mockito.times(1)).toDTO(Mockito.any(Alternative.class));
    }


    @Test
    @DisplayName("Deve lançar AlternativeException quando houver erro ao tentar salvar uma lista de alternativas")
    void deveLancarAlternativeExceptionErroAoSalvarUmaListaDeAlternativas() {
        final var alternativeEntity = AlternativeFactory.createValidAlternative();
        Mockito.when(repository.saveAll(Mockito.anyList())).thenThrow(AlternativeException.class);

        Assertions.assertThrows(AlternativeException.class, () -> sut.saveAll(List.of(alternativeEntity)));

        Mockito.verify(repository, Mockito.times(1)).saveAll(List.of(alternativeEntity));
    }

    @Test
    @DisplayName("Deve retornar lista de contagem de alternativas por temas")
    void deveRetornarListaDeContagemDeQuestoesPorTema() {
        String themeName = "Tema questão";
        List<Object[]> listagem = new ArrayList<>();
        Mockito.when(repository.countAlternativesByTheme()).thenReturn(listagem);

        List<Object[]> retorno = sut.countAlternativesByTheme();

        Assertions.assertEquals(listagem, retorno);
    }
}
