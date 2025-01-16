package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertMessageRequest {

    @NotBlank(message = "Сообщение должно быть заполнено")
    @Size(min = 2, max = 100, message = "Сообщение должно содержать не менее {min} символов и не более {max} символов")
    private String message;
}
