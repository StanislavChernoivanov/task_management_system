package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sChernoivanov.taskManagementSystem.exception.EntityNotFoundException;
import ru.sChernoivanov.taskManagementSystem.model.entity.*;
import ru.sChernoivanov.taskManagementSystem.model.repository.TaskRepository;
import ru.sChernoivanov.taskManagementSystem.model.repository.specification.TaskSpecification;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;
import ru.sChernoivanov.taskManagementSystem.util.BeanUtils;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.pagination.RequestPageableModel;

import java.lang.reflect.Field;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;


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
    public Task update(Long id, Task task) {
        Task updatedtask = findById(id);
        BeanUtils.copyNotNullProperties(task, updatedtask);
        
        return taskRepository.save(updatedtask);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () ->  new EntityNotFoundException(String.format("Задача с id - %s не найдена", id))
        );
    }

    @Override
    public List<Task> findAll(RequestPageableModel paginationData) {

        return taskRepository.findAll(
                PageRequest.of(paginationData.getPageNumber(), paginationData.getPageSize())
            ).stream().toList();
    }


    @Override
    public List<Task> filterBy(Status status,
                               Priority priority,
                               Long authorId,
                               Long performerId,
                               RequestPageableModel model) {

        return taskRepository.findAll(
                TaskSpecification.withFilter(status, priority, authorId, performerId),
                PageRequest.of(model.getPageNumber(),
                        model.getPageSize())).stream().toList();
    }


    @Override
    public Task assignPerformer(Long userId, Long taskId) {
        User performer = userService.findById(userId);
        Task task = findById(taskId);
        task.setPerformer(performer);
        
        return taskRepository.save(task);
    }

    @Override
    public Task addMessage(Message message, Long userId, Long taskId) {
        User author = userService.findById(userId);
        Task task = findById(taskId);
        author.getMessages().add(message);
        task.getMessages().add(message);
        message.setAuthor(author);
        message.setTask(task);
        task.setAuthor(author);

        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Status status, Long taskId) {
        Task task = findById(taskId);
        task.setStatus(status);

        return taskRepository.save(task);
    }
}
