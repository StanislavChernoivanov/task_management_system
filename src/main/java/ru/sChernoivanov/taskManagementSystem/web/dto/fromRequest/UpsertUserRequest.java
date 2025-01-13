package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertUserRequest {

    private String name;
    private String email;
    private String password;
}
