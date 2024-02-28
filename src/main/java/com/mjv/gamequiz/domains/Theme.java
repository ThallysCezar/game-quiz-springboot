package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@SequenceGenerator(name = "t_theme_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_theme_seq")
    private Long id;

    @NotBlank
    @NotNull
    private String theme;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;
}
