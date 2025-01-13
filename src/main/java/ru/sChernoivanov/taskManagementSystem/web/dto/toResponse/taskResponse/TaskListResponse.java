package ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskListResponse {

    private List<TaskResponse> tasks;
}
