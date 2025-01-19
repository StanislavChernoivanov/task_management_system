package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
