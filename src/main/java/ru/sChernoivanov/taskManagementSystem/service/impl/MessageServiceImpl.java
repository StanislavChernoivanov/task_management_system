package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.MessageRepository;
import ru.sChernoivanov.taskManagementSystem.service.MessageService;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final TaskService taskService;


    @Override
    public Message create(Long userId, Long taskId, Message message) {
        User author = userService.findById(userId);
        Task task = taskService.findById(taskId);
        author.getMessages().add(message);
        task.getMessages().add(message);
        message.setAuthor(author);
        message.setTask(task);

        return messageRepository.save(message);
    }
}
