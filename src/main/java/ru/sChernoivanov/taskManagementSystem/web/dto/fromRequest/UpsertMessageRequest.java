package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertMessageRequest {

    private String message;
}
