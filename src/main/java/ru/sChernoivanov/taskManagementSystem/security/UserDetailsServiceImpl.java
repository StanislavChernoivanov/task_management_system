package ru.sChernoivanov.taskManagementSystem.security;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.model.repository.UserRepository;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return UserDetailsImpl.builder().user(userService.findByEmail(username)).build();
    }
}