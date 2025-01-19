package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "refresh token request")
public class RefreshTokenRequest {

    @Schema(description = "refresh token", minLength = 300, maxLength = 1000)
    @NotBlank(message = "Укажите токен")
    @Size(min = 2, max = 500, message = "Токен должен содержать не менее {min} символов и не более {max} символов")
    private String refreshToken;
}
