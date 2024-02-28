package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@SequenceGenerator(name = "t_question_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_question_seq")
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String answer;

    @NotBlank
    @Column(length = 200, nullable = false)
    private String response;

    @Column(name = "correct_alternativeid")
    private Long correctAlternativeID;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<QuestionChoices> alternativeList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_theme", referencedColumnName = "id")
    private Theme theme;

}