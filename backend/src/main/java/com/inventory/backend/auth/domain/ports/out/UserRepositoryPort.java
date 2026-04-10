package com.inventory.backend.auth.domain.ports.out;

import com.inventory.backend.auth.domain.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    User save(User user);
}
