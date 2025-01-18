package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.messageResponse.MessageResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse.UserResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    @Schema(description = "task id")
    private Long id;

    @Schema(description = "task header")
    private String header;

    @Schema(description = "task description")
    private String description;

    @Schema(description = "task execution status", allowableValues = {"WAIT", "IN_PROCESS", "DONE"})
    private Status status;

    @Schema(description = "task priority", allowableValues = {"LOW", "MIDDLE", "HIGH"})
    private Priority priority;

    @Schema(description = "author info")
    private UserResponse author;

    @Schema(description = "performer info")
    private UserResponse performer;

    @Schema(description = "message's list")
    private List<MessageResponse> messages;

}
