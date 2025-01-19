package ru.sChernoivanov.taskManagementSystem.contoller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sChernoivanov.taskManagementSystem.exception.AlreadyExistsException;
import ru.sChernoivanov.taskManagementSystem.security.SecurityService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.CreateUserRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.LoginRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.RefreshTokenRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.AuthResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.ErrorResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.RefreshTokenResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.SimpleResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;


    @Operation(
            summary = "account login",
            description = "account login" +
                    ", return message auth data",
            tags = {"auth", "signin"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = AuthResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody @Valid LoginRequest loginRequest) {

        return ResponseEntity.ok(securityService.authenticateUser(loginRequest));
    }


    @Operation(
            summary = "Create new account",
            description = "Create new account" +
                    ", return message that user is created",
            tags = {"auth", "signup"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = SimpleResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("/signup")
    public ResponseEntity<SimpleResponse> signup(@RequestBody @Valid CreateUserRequest userRequest) {
        if (userService.existsByName(userRequest.getName())) {
            throw new AlreadyExistsException("Username already exists. " +
                    "Check that the input data is correct");
        }

        if (userService.existsByEmail(userRequest.getEmail())) {
            throw new AlreadyExistsException("Email already exists. " +
                    "Check that the input data is correct");
        }

        securityService.register(userRequest);

        return ResponseEntity.ok(SimpleResponse.builder().message("user is created").build());
    }




    @Operation(
            summary = "Refresh token",
            description = "Refresh token" +
                    ", return new access token and refresh token",
            tags = {"auth", "signup"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = RefreshTokenResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshTokenResponseResponseEntity(
            @RequestBody @Valid RefreshTokenRequest request) {

        return ResponseEntity.ok(securityService.refreshToken(request));
    }

}