package com.mjv.gamequiz.domains;

import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question-alternative")
public class QuestionAlternative {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String alternative;

    @NotBlank
    @NotNull
    private Boolean itsCorrect;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String reference;

    @NotBlank
    @NotNull
    private Long questionId;

    private ArrayList<QuestionAlternativeDTO> questionAlternativeList;

}