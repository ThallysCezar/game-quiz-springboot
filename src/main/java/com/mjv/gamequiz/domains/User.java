package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String name;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String fullName;

    @NotBlank
    @NotNull
    @Min(value = 18, message = "A idade mínima permitida é 18 anos.")
    private Integer age;

    @NotBlank
    @NotNull
    @Email(message = "O e-mail fornecido não é válido.")
    private String email;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres.")
    private String password;

}