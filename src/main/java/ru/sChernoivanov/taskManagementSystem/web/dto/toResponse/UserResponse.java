package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "user info")
public class UserResponse {

    @Schema(description = "user's id")
    private Long id;

    @Schema(description = "user name")
    private String name;

    @Schema(description = "user email")
    private String email;

    @Schema(description = "task are created by this user")
    private List<TaskResponse> createdTasks;

}
