package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sChernoivanov.taskManagementSystem.exception.EntityNotFoundException;
import ru.sChernoivanov.taskManagementSystem.exception.OperationNotAvailableException;
import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.UserRepository;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "User with id is - %s not found", userId
                ))
        );
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "User with name is - %s not found", username
                ))
        );
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "User with email is - %s not found", email
                ))
        );
    }

    @Override
    public void checkAccessByUser(String username, Task task) {
        User user = findByUsername(username);

        if (!user.getRoleTypes().contains(RoleType.ADMIN) && !task.getPerformer().getName().equals(username)) {
            throw new OperationNotAvailableException(MessageFormat.format(
                    "User named {0} does not have access to this operation", username
            ));
        }

    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
