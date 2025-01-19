package ru.sChernoivanov.taskManagementSystem.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;
import ru.sChernoivanov.taskManagementSystem.exception.RefreshTokenException;
import ru.sChernoivanov.taskManagementSystem.model.entity.RefreshToken;

@Component
@Slf4j
public class RedisExpirationEvent {

    @EventListener
    public void handleRedisKeyExpiredEvent(RedisKeyExpiredEvent<RefreshToken> event) {
        RefreshToken expiredRefreshToken = (RefreshToken) event.getValue();

        if (expiredRefreshToken == null) {
            throw new RefreshTokenException("Refresh token's lifetime has expired." +
                    "Repeat signin action");
        }

        log.info("Refresh token with key = {} has expired RefreshToken is: {}",
                expiredRefreshToken.getId(), expiredRefreshToken.getToken());

    }
}