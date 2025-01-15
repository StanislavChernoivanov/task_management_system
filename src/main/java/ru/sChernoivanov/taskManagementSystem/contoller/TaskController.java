package ru.sChernoivanov.taskManagementSystem.contoller;

import com.azure.core.annotation.Patch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sChernoivanov.taskManagementSystem.mapping.TaskMapper;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertTaskRequest;
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
    public ResponseEntity<TaskListResponse> findAll() {
        return ResponseEntity.ok(
                taskMapper.taskListToTaskResponseList(
                        taskService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(taskService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody UpsertTaskRequest taskRequest,
                                               @RequestParam Long authorId,
                                               @RequestParam Long performerId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.taskToResponse(
                        taskService.create(authorId, performerId, taskMapper.requestToTask(taskRequest)
                )));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long taskId,
                                               @RequestParam Long userId,
                                               @RequestBody UpsertTaskRequest taskRequest) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.update(taskId, taskMapper.requestToTask(taskId, taskRequest)
                )
        ));
    }


    @PatchMapping("/performer/{id}")
    public ResponseEntity<TaskResponse> assignPerformer(@PathVariable Long taskId,
                                                        @RequestParam Long authorId,
                                                        @RequestParam Long performerId) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.assignPerformer(authorId, performerId, taskId)
                )
        );
    }

    @PatchMapping("/message/{id}")
    public ResponseEntity<TaskResponse> addMessage(@PathVariable Long taskId,
                                                   @RequestParam Long userId,
                                                   @RequestBody Message message) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.addMessage(message, taskId, userId)
                )
        );
    }


    @PatchMapping("/status/{id}")
    public ResponseEntity<TaskResponse> changeStatus(@PathVariable Long taskId,
                                                     @RequestParam Long userId,
                                                     @RequestBody Status status) {
        return ResponseEntity.ok(
                taskMapper.taskToResponse(
                        taskService.changeStatus(status, taskId, userId)
                )
        );
    }

}
