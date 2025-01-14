package ru.sChernoivanov.taskManagementSystem.service;

import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;

import java.util.List;

public interface TaskService {

    Task create(Long authorId, Long performerId, Task task);

    Task update(Long id, Task task);

    Task findById(Long id);

    List<Task> findAll();

    Task assignPerformer(Long userId);

    Task addMessage(Message message);

    Task changeStatus(Status status);
}
