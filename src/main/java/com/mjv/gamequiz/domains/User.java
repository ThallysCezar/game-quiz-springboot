package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @OneToOne(mappedBy = "user")
    @JdbcTypeCode(SqlTypes.JSON)
    private Player player;

}