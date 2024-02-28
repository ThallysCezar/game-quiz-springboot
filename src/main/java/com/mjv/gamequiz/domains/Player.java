package com.mjv.gamequiz.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SequenceGenerator(name = "t_player_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_player_seq")
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 200, nullable = false)
    private String nickName;

    @NotNull
    private Integer score;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private User user;

}