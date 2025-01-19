package ru.sChernoivanov.taskManagementSystem.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sChernoivanov.taskManagementSystem.model.entity.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);
}
