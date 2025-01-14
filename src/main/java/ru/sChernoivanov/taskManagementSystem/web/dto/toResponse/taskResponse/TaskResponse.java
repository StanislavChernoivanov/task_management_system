package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse;

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

    private Long id;
    private String header;
    private String description;
    private Status status;
    private Priority priority;
    private UserResponse author;
    private UserResponse performer;
    private List<MessageResponse> messages;

}
