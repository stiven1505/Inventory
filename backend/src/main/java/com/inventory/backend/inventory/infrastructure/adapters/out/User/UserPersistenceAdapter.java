package com.inventory.backend.inventory.infrastructure.adapters.out.User;

import com.inventory.backend.inventory.domain.model.User;
import com.inventory.backend.inventory.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(ent -> new User(ent.getId(), ent.getUsername(), ent.getPassword(), ent.getRole()));
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        
        UserEntity saved = userJpaRepository.save(entity);
        return new User(saved.getId(), saved.getUsername(), saved.getPassword(), saved.getRole());
    }
}