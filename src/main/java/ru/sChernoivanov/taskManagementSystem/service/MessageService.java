package ru.sChernoivanov.taskManagementSystem.service;

import ru.sChernoivanov.taskManagementSystem.model.entity.Message;

public interface MessageService {

    Message create(Long userId, Long taskId, Message message);

}
