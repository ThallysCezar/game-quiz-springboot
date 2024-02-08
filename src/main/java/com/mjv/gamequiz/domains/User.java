package com.mjv.gamequiz.domains;

import com.mjv.gamequiz.domains.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements UserDetails {
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

    @NotNull
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}