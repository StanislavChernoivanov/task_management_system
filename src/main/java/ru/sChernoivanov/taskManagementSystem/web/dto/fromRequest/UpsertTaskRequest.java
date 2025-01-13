package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertTaskRequest {

    private String header;
    private String description;
    private Status status;
    private Priority priority;
}
