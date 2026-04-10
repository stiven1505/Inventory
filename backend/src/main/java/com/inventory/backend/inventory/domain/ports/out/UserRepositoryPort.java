package com.inventory.backend.inventory.domain.ports.out;

import com.inventory.backend.inventory.domain.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    User save(User user);
}