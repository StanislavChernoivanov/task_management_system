package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {

    @NotBlank(message = "Имя должно быть заполнено")
    @Size(min = 2, max = 30, message = "Имя должно содержать не менее {min} символов и не более {max} символов")
    private String name;

    @NotBlank(message = "Почта должна быть указана")
    @Size(min = 5, max = 80, message = "Почта должна содержать не менее {min} символов и не более {max} символов")
    private String email;

    @NotBlank(message = "Пароль должен быть указан")
    @Size(min = 2, max = 100, message = "Пароль должен содержать не менее {min} символов и не более {max} символов")
    private String password;
}
