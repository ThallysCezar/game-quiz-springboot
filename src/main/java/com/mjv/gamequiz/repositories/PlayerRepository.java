package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findById(Long id);
}
