package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.messageResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse.TaskResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse.UserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "message info")
public class MessageResponse {

    @Schema(description = "message id")
    private Long id;

    @Schema(description = "message text")
    private String message;

    @Schema(description = "message author info")
    private UserResponse author;
}
