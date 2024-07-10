package com.ls.security.global.user.domain.dao.impl;

import com.ls.security.global.user.domain.dao.UserDAO;
import com.ls.security.global.user.domain.model.User;
import com.ls.security.global.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    public User save(User currentUser) {
        return userRepository.save(currentUser);
    }
}
