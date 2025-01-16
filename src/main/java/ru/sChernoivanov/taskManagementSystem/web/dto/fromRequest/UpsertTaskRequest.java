package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

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
public class UpsertTaskRequest {

    @NotBlank(message = "Заголовок должен быть заполнен")
    @Size(min = 3, max = 50, message = "Заголовок содержать не менее {min} символов и не более {max} символов")
    private String header;

    @NotBlank(message = "Описание должно быть заполнено")
    @Size(min = 5, max = 1000, message = "Описание должно содержать не менее {min} символов и не более {max} символов")
    private String description;

    private Status status;


    private Priority priority;
}
