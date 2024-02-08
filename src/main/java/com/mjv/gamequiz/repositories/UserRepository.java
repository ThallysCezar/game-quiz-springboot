package com.mjv.gamequiz.repositories;

import com.mjv.gamequiz.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findByEmailAndPassword(String email, String password);

    //    @Query(value = "SELECT *" +
//                    "FROM t_user usuario " +
//                    "WHERE usuario.email = :email " +
//                     "AND usuario.password = :password", nativeQuery = true)
    Optional<User> existsByEmailAndPassword(String email, String password);

    UserDetails findByEmail(String email);
}