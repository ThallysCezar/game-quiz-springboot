package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SequenceGenerator(name = "t_question_choices_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question_choices")
public class QuestionChoices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_question_choices_seq")
    private Long id;

    @NotBlank
    @NotNull
    private String alternative;

    @NotBlank
    @NotNull
    private Boolean itsCorrect;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String content;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_question", referencedColumnName = "id")
    private Question question;

}