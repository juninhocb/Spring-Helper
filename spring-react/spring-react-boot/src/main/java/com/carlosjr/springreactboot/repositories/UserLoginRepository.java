package com.carlosjr.springreactboot.repositories;

import com.carlosjr.springreactboot.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findUserByUsername(String username);
}
