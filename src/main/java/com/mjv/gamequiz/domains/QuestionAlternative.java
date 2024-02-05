package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question-alternative")
public class QuestionAlternative implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question", referencedColumnName = "id")
    private Question question;

}