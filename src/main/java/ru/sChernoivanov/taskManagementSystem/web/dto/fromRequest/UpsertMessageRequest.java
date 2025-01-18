package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "message entity")
public class UpsertMessageRequest {

    @NotBlank(message = "Сообщение должно быть заполнено")
    @Size(min = 2, max = 500, message = "Сообщение должно содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "message text", minLength = 2, maxLength = 500)
    private String message;
}
