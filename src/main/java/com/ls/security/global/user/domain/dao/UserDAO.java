package com.ls.security.global.user.domain.dao;


import com.ls.security.global.user.domain.model.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByAccountId(String accountId);

    User save(User currentUser);
}
