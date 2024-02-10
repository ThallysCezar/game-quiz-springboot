package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);
    Optional<User> findByLoginAndPassword(String login, String password);
    Optional<User> existsByLoginAndPassword(String login, String password);
    UserDetails findByLogin(String login);

}