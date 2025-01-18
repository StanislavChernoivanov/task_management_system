package ru.sChernoivanov.taskManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sChernoivanov.taskManagementSystem.exception.EntityNotFoundException;
import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.UserRepository;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "Пользователь с id - %s не найден", userId
                ))
        );
    }

    @Override
    public User createNewAccount(User user, RoleType roleType) {
        user.getRoleTypes().add(roleType);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(
                () -> new EntityNotFoundException(String.format(
                        "Пользователь с именем - %s не найден", username
                ))
        );
    }

    @Override
    public void checkAccessByUser(String username, Long userId) {

    }
}
