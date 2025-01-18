package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "task entity")
public class UpsertTaskRequest {

    @NotBlank(message = "Заголовок должен быть заполнен")
    @Size(min = 3, max = 50, message = "Заголовок содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "task header", minLength = 3, maxLength = 50)
    private String header;

    @NotBlank(message = "Описание должно быть заполнено")
    @Size(min = 5, max = 1000, message = "Описание должно содержать не менее {min} символов и не более {max} символов")
    @Schema(description = "task description", minLength = 5, maxLength = 1000)
    private String description;

    @Schema(description = "task status", allowableValues = {"WAIT", "IN_PROCESS", "DONE"})
    private Status status;

    @Schema(description = "task priority", allowableValues = {"LOW", "MIDDLE", "HIGH"})
    private Priority priority;
}
