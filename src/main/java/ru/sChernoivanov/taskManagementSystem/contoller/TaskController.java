package ru.sChernoivanov.taskManagementSystem.contoller;

import com.azure.core.annotation.Patch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.sChernoivanov.taskManagementSystem.mapping.TaskMapper;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertTaskRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.pagination.RequestPageableModel;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse.TaskListResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse.TaskResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/v1/task"))
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<TaskListResponse> findAll(@RequestBody @Valid RequestPageableModel requestPageableModel) {

        return ResponseEntity.ok(
                taskMapper.taskListToTaskResponseList(
                        taskService.findAll(requestPageableModel)
                )
        );
    }


    @GetMapping("/filterBy")
    public ResponseEntity<TaskListResponse> filterBy(
                                            @RequestParam Status status,
                                            @RequestParam Priority priority,
                                            @RequestParam
                                                @NotNull(message = "Задайте параметр")
                                                @Positive(message = "Параметр должен быть больше 0") Long authorId,
                                            @RequestParam
                                                @NotNull(message = "Задайте параметр")
                                                @Positive(message = "Параметр должен быть больше 0") Long performerId,
                                            @RequestBody @Valid RequestPageableModel model) {

        return ResponseEntity.ok(
                taskMapper.taskListToTaskResponseList(
                        taskService.filterBy(status, priority, authorId, performerId, model)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable
                                                     @Positive(message = "Параметр должен быть больше 0")
                                                     @NotNull(message = "Задайте параметр") Long id) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(taskService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid UpsertTaskRequest taskRequest,
                                               @RequestParam
                                                    @Positive(message = "Параметр должен быть больше 0")
                                                    @NotNull(message = "Задайте параметр") Long authorId,
                                               @RequestParam
                                                    @Positive(message = "Параметр должен быть больше 0")
                                                    @NotNull(message = "Задайте параметр") Long performerId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.taskToResponse(
                        taskService.create(authorId, performerId, taskMapper.requestToTask(taskRequest)
                )));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable
                                                    @Positive(message = "Параметр должен быть больше 0")
                                                    @NotNull(message = "Задайте параметр") Long taskId,
                                               @RequestBody @Valid UpsertTaskRequest taskRequest) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.update(taskId, taskMapper.requestToTask(taskId, taskRequest)
                )
        ));
    }


    @PatchMapping("/performer/{id}")
    public ResponseEntity<TaskResponse> assignPerformer(@PathVariable
                                                            @Positive(message = "Параметр должен быть больше 0")
                                                            @NotNull(message = "Задайте параметр") Long taskId,
                                                        @RequestParam
                                                            @Positive(message = "Параметр должен быть больше 0")
                                                            @NotNull(message = "Задайте параметр") Long authorId,
                                                        @RequestParam
                                                            @Positive(message = "Параметр должен быть больше 0")
                                                            @NotNull(message = "Задайте параметр") Long performerId) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.assignPerformer(authorId, performerId, taskId)
                )
        );
    }

    @PatchMapping("/message/{id}")
    public ResponseEntity<TaskResponse> addMessage(@PathVariable
                                                       @Positive(message = "Параметр должен быть больше 0")
                                                       @NotNull(message = "Задайте параметр") Long taskId,
                                                   @RequestParam
                                                       @Positive(message = "Параметр должен быть больше 0")
                                                       @NotNull(message = "Задайте параметр") Long userId,
                                                   @RequestBody @Valid Message message) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.addMessage(message, taskId, userId)
                )
        );
    }


    @PatchMapping("/status/{id}")
    public ResponseEntity<TaskResponse> changeStatus(@PathVariable
                                                         @Positive(message = "Параметр должен быть больше 0")
                                                         @NotNull(message = "Задайте параметр") Long taskId,
                                                     @RequestParam
                                                         @Positive(message = "Параметр должен быть больше 0")
                                                         @NotNull(message = "Задайте параметр") Long userId,
                                                     @RequestBody @Valid Status status) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.changeStatus(status, taskId, userId)
                )
        );
    }

}
