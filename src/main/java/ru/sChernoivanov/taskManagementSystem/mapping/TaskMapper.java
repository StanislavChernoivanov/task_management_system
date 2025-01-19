package ru.sChernoivanov.taskManagementSystem.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertTaskRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.TaskListResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.TaskResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {


    Task requestToTask(UpsertTaskRequest upsertTaskRequest);

    @Mapping(source = "taskId", target = "id")
    Task requestToTask(Long taskId, UpsertTaskRequest upsertTaskRequest);


    TaskResponse taskToResponse(Task task);

    List<TaskResponse> taskListToResponseList(List<Task> tasks);

    default TaskListResponse taskListToTaskResponseList(List<Task> tasks) {
        var taskListResponse = new TaskListResponse();
        taskListResponse.setTasks(taskListToResponseList(tasks));
        return taskListResponse;

    }
}
