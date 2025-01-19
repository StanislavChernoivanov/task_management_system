package ru.sChernoivanov.taskManagementSystem.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.sChernoivanov.aop.CheckAccess;
import ru.sChernoivanov.taskManagementSystem.mapping.TaskMapper;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.RequestPageableModel;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertTaskRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.ErrorResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.TaskListResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.TaskResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/v1/task"))
@Tag(name = "Task v1", description = "Task API v1")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final UserService userService;


    @Operation(
            summary = "Get all tasks are filtered by author id,  performer id, status, and priority",
            description = "Get all tasks are filtered by author id,  performer id, status, and priority" +
                    ", return task's list",
            tags = {"task", "performer id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskListResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filterBy")
    public ResponseEntity<TaskListResponse> filterBy(
            @RequestParam
            @Parameter(description = "task status",
                    allowEmptyValue = true,
                    examples = {
                            @ExampleObject(value = "WAIT"),
                            @ExampleObject(value = "IN_PROCESS"),
                            @ExampleObject(value = "DONE")
                    }) Status status,
            @RequestParam
            @Parameter(description = "task priority",
                    allowEmptyValue = true,
                    examples = {
                            @ExampleObject(value = "LOW"),
                            @ExampleObject(value = "MIDDLE"),
                            @ExampleObject(value = "HIGH")
                    }) Priority priority,
            @RequestParam
            @Positive(message = "Параметр должен быть больше 0")
            @NotNull(message = "Задайте параметр")
            @Parameter(description = "performer id",
                    allowEmptyValue = true) Long performerId,
            @RequestBody @Valid RequestPageableModel model,
            @RequestParam
            @Positive(message = "Параметр должен быть больше 0")
            @NotNull(message = "Задайте параметр")
            @Parameter(description = "author id",
                    allowEmptyValue = true) Long authorId) {

        return ResponseEntity.ok(
                taskMapper.taskListToTaskResponseList(
                        taskService.filterBy(status, priority, authorId, performerId, model)
                )
        );
    }


    @Operation(
            summary = "Get task by id",
            description = "Get task by id, return task",
            tags = {"task", "task id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable
                                                 @Positive(message = "Параметр должен быть больше 0")
                                                 @NotNull(message = "Задайте параметр")
                                                 @Parameter(description = "task id",
                                                         required = true) Long id) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(taskService.findById(id))
        );
    }


    @Operation(
            summary = "Create new task",
            description = "Create new task, return task",
            tags = {"task", "performer id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TaskResponse> create(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody @Valid UpsertTaskRequest taskRequest,
                                               @RequestParam(required = false)
                                               @Positive(message = "Параметр должен быть больше 0")
                                               @Parameter(description = "performer id") Long performerId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.taskToResponse(
                        taskService.create(userService.findByEmail(userDetails.getUsername()).getId(),
                                performerId,
                                taskMapper.requestToTask(taskRequest)
                        )));
    }


    @Operation(
            summary = "Update task by id",
            description = "Update task by id" +
                    ", return updated task",
            tags = {"task", "task id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable
                                               @Positive(message = "Параметр должен быть больше 0")
                                               @NotNull(message = "Задайте параметр")
                                               @Parameter(description = "task id",
                                                       required = true) Long taskId,
                                               @RequestBody @Valid UpsertTaskRequest taskRequest) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.update(taskId, taskMapper.requestToTask(taskId, taskRequest)
                        )
                ));
    }


    @Operation(
            summary = "Assign performer for task",
            description = "Assign performer for task" +
                    ", return task are assigned performer",
            tags = {"task", "task id", "performer id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/performer/{id}")
    public ResponseEntity<TaskResponse> assignPerformer(@PathVariable
                                                        @Positive(message = "Параметр должен быть больше 0")
                                                        @NotNull(message = "Задайте параметр")
                                                        @Parameter(description = "task id",
                                                                required = true) Long taskId,
                                                        @RequestParam
                                                        @Positive(message = "Параметр должен быть больше 0")
                                                        @NotNull(message = "Задайте параметр")
                                                        @Parameter(description = "performer id",
                                                                required = true) Long performerId) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.assignPerformer(performerId, taskId)
                )
        );
    }


    @Operation(
            summary = "Add message for task",
            description = "Add message for task" +
                    ", return task are added new message",
            tags = {"task", "task id", "performer id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(
                    responseCode = "403",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @CheckAccess
    @PatchMapping("/message/{id}")
    public ResponseEntity<TaskResponse> addMessage(@AuthenticationPrincipal UserDetails userDetails,
                                                   @PathVariable
                                                   @Positive(message = "Параметр должен быть больше 0")
                                                   @NotNull(message = "Задайте параметр")
                                                   @Parameter(description = "task id",
                                                           required = true) Long taskId,
                                                   @RequestBody @Valid Message message) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.addMessage(message,
                                userService.findByEmail(userDetails.getUsername()).getId(),
                                taskId)
                )
        );
    }


    @Operation(
            summary = "Change status for task",
            description = "Change status for task" +
                    ", return task are changed status",
            tags = {"task", "task id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(
                    responseCode = "403",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json")

            )
    })
    @CheckAccess
    @PatchMapping("/status/{id}")
    public ResponseEntity<TaskResponse> changeStatus(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable
                                                     @Positive(message = "Параметр должен быть больше 0")
                                                     @NotNull(message = "Задайте параметр") Long taskId,
                                                     @RequestBody @Valid Status status) {

        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.changeStatus(status, taskId)
                )
        );
    }

}
