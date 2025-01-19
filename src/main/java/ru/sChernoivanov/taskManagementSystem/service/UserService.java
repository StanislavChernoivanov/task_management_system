package ru.sChernoivanov.taskManagementSystem.service;

import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;

public interface UserService {

    User findById(Long userId);

    User findByUsername(String username);

    User findByEmail(String email);

    void checkAccessByUser(String username, Task task);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
