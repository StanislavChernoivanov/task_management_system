package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "refresh token response")
public class RefreshTokenResponse {

    @Schema(description = "access token")
    private String accessToken;

    @Schema(description = "refresh token")
    private String refreshToken;

}
