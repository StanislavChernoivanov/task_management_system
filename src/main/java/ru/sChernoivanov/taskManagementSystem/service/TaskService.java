package ru.sChernoivanov.taskManagementSystem.service;

import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.RequestPageableModel;

import java.util.List;

public interface TaskService {

    Task create(Long authorId, Long performerId, Task task);

    Task update(Long id, Task task);

    Task findById(Long id);

    List<Task> findAll(RequestPageableModel paginationData);

    List<Task> filterBy(Status status,
                        Priority priority,
                        Long authorId,
                        Long performerId,
                        RequestPageableModel model);

    Task assignPerformer(Long userId, Long taskId);

    Task addMessage(Message message, Long userId, Long taskId);

    Task changeStatus(Status status, Long taskId);
}

