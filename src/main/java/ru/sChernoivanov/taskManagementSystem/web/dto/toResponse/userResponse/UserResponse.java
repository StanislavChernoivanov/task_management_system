package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.messageResponse.MessageResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse.TaskResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;

    private List<TaskResponse> createdTasks;

    private List<MessageResponse> messages;
}
