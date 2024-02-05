package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 200, nullable = false)
    private String response;

    private Long correctQuestionAlternativeID;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionAlternative> questionAlternativeArrayList;

}