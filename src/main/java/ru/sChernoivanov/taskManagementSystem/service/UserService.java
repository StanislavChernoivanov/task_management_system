package ru.sChernoivanov.taskManagementSystem.service;

import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long userId);

    User createNewAccount(User user, RoleType roleType);

    User findByUsername(String username);

    void checkAccessByUser(String username, Long userId);
}
