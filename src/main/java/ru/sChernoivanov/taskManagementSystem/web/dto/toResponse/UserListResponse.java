package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "user info list")
public class UserListResponse {

    @Schema(description = "user's list")
    private List<UserResponse> users;
}
