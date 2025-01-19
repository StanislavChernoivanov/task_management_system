package ru.sChernoivanov.taskManagementSystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sChernoivanov.taskManagementSystem.exception.RefreshTokenException;
import ru.sChernoivanov.taskManagementSystem.model.entity.RefreshToken;
import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.UserRepository;
import ru.sChernoivanov.taskManagementSystem.security.jwt.JwtUtils;
import ru.sChernoivanov.taskManagementSystem.service.impl.RefreshTokenService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.CreateUserRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.LoginRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.RefreshTokenRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.AuthResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.RefreshTokenResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return AuthResponse.builder()
                .id(userDetails.getId())
                .token(jwtUtils.generateJwtToken(userDetails))
                .refreshToken(refreshToken.getToken())
                .name(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public void register(CreateUserRequest createUSerRequest) {
        User user = User.builder()
                .name(createUSerRequest.getName())
                .email(createUSerRequest.getEmail())
                .password(passwordEncoder.encode(createUSerRequest.getPassword()))
                .roles(createUSerRequest.getRoles())
                .roleTypes(createUSerRequest.getRoles().stream().map(
                        role -> RoleType.valueOf(role.name().toUpperCase())).toList()
                        )
                .build();
        userRepository.save(user);
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        return refreshTokenService.findByRefreshToken(refreshToken)
                .map(refreshTokenService::checkedRefreshToken)
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    User tokenOwner = userRepository
                            .findById(userId)
                            .orElseThrow(() ->
                                    new RefreshTokenException("Exception trying to get token for userId: " + userId));

                    String token = jwtUtils.generateTokenFromEmail(tokenOwner.getName());

                    return new RefreshTokenResponse(token, refreshTokenService.createRefreshToken(userId).getToken());
                }).orElseThrow(() -> new RefreshTokenException(refreshToken, "Refresh token notFound"));
    }

}