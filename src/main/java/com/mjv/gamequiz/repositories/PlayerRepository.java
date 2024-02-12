package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.dtos.RankingTopDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findById(Long id);
    Optional<Player> findByNickName(String nickName);

    @Query(value = "SELECT p.nick_name, p.score " +
                   "FROM t_player p " +
                   "ORDER BY p.score DESC", nativeQuery = true)
    List<Object[]> findAllByRanking();

}