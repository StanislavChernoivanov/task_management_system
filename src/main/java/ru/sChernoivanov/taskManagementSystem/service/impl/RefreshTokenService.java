package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sChernoivanov.taskManagementSystem.exception.RefreshTokenException;
import ru.sChernoivanov.taskManagementSystem.model.entity.RefreshToken;
import ru.sChernoivanov.taskManagementSystem.model.repository.RefreshTokenRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${app.jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findByRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .expireDate(Instant.now().plusMillis(refreshTokenExpiration.toMillis()))
                .token(UUID.randomUUID().toString())
                .build();

        token = refreshTokenRepository.save(token);

        return token;
    }

    public RefreshToken checkedRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(), "Refresh token was expired. Repeat signin action");
        }

        return refreshToken;
    }
}
