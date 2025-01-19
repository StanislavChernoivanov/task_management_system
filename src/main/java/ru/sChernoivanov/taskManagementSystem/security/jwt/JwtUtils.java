package ru.sChernoivanov.taskManagementSystem.security.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sChernoivanov.taskManagementSystem.security.UserDetailsImpl;

import java.time.Duration;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwt.tokenExpiration}")
    private Duration tokenExpiration;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetailsImpl userDetails) {
        return generateTokenFromEmail(userDetails.getUsername());
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(new Date().getTime() + tokenExpiration.toMillis()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid signature: {}", e.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid token: {} ", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Token is expired: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Token is unsupported: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Claims string is empty: {}", ex.getMessage());
        }
        return false;
    }
}
