package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
