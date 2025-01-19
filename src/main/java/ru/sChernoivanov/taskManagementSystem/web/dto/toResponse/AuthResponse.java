package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {


    @Schema(description = "user's id")
    private Long id;

    @Schema(description = "user name")
    private String name;

    @Schema(description = "user email")
    private String email;

    @Schema(description = "access token")
    private String token;

    @Schema(description = "refresh token")
    private String refreshToken;

    @Schema(description = "list of roles")
    private List<String> roles;

}
