package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Optional<Theme> findByTheme(String themeName);
}
