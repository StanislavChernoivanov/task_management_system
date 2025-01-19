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
@Schema(description = "simple response")
public class SimpleResponse {


    @Schema(description = "response message")
    private String message;
}
