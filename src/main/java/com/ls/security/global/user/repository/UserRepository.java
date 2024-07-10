package com.ls.security.global.user.repository;

import com.ls.security.global.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByAccountId(String accountId);
}
