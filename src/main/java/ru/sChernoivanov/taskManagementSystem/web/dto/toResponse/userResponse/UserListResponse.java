package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {

    private List<UserResponse> users;
}
