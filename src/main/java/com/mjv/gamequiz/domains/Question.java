package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String theme;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String question;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String response;

    private Long correctQuestionAlternativeID;
    private List<QuestionAlternative> questionAlternativeArrayList;

}