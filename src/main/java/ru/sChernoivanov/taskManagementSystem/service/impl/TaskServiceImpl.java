package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.TaskRepository;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

import java.lang.reflect.Field;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final MessageServiceImpl messageServiceImpl;


    @Override
    public Task create(Long authorId, Long performerId, Task task) {
        User author = userService.findById(authorId);
        User performer = userService.findById(performerId);
        author.getCreatedTasks().add(task);
        task.setAuthor(author);
        task.setPerformer(performer);

        return taskRepository.save(task);
    }

    @Override
    @SneakyThrows
    public Task update(Long id, Task task) {
        Task updatedtask = findById(id);

        Class<? extends Task> clazz = task.getClass();
        Field [] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(task);
            if(value != null) {
                field.set(updatedtask, value);
            }
        }
        
        return taskRepository.save(updatedtask);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () ->  new RuntimeException(String.format("Задача с id - %s не найдена", id))
        );
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task assignPerformer(Long userId, Long taskId) {
        User performer = userService.findById(userId);
        Task task = findById(taskId);
        task.setPerformer(performer);
        
        return taskRepository.save(task);
    }

    @Override
    public Task addMessage(Message message) {

        // TODO: 14.01.2025 доделать!!! 
        return null;
    }

    @Override
    public Task changeStatus(Status status) {
        return null;
    }
}