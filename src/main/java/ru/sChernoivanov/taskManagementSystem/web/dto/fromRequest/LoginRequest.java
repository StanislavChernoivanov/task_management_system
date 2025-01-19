package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "Почта должна быть указана")
    @Size(min = 5, max = 80, message = "Почта должна содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "user email", minLength = 5, maxLength = 80, example = "user@user.com")
    private String email;

    @NotBlank(message = "Пароль должен быть указан")
    @Size(min = 5, max = 15, message = "Пароль должен содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "user password", minLength = 5, maxLength = 15)
    private String password;
}
