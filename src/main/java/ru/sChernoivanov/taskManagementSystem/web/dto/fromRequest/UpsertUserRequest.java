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
@Schema(description = "user entity")
public class UpsertUserRequest {

    @NotBlank(message = "Имя должно быть заполнено")
    @Size(min = 2, max = 30, message = "Имя должно содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "user name", minLength = 2, maxLength = 30)
    private String name;

    @NotBlank(message = "Почта должна быть указана")
    @Size(min = 5, max = 80, message = "Почта должна содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "user email", minLength = 5, maxLength = 80, example = "user@user.com")
    private String email;

    @NotBlank(message = "Пароль должен быть указан")
    @Size(min = 5, max = 15, message = "Пароль должен содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "user password", minLength = 5, maxLength = 15)
    private String password;
}
