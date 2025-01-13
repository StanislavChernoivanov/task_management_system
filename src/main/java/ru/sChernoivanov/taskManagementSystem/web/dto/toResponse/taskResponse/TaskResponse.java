package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
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
    
    
    public static TaskResponse toTaskResponse(Task task) {
        var taskResponse = new TaskResponse();
        var author = new UserResponse();
        var performer = new UserResponse();
        
        if(task.getId() != null) taskResponse.setId(task.getId());
        if(task.getHeader() != null && !task.getHeader().isBlank()) taskResponse.setHeader(task.getHeader());
        if(task.getDescription() != null && !task.getDescription().isBlank()) taskResponse.setDescription(task.getDescription());
        if(task.getStatus() != null) taskResponse.setStatus(task.getStatus());
        if(task.getPriority() != null) taskResponse.setPriority(task.getPriority());
        if(task.getAuthor() != null) {
        }
        // TODO: 13.01.2025 доделать, придумать 
    }
}
