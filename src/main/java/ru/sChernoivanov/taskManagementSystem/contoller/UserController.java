package ru.sChernoivanov.taskManagementSystem.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sChernoivanov.taskManagementSystem.mapping.UserMapper;
import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.service.UserService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertUserRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse.UserResponse;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }


    @PostMapping("/createAcc")
    public ResponseEntity<UserResponse> createAccount(@RequestBody UpsertUserRequest userRequest,
                                                      @RequestParam RoleType roleType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.createNewAccount(
                                userMapper.requestToUser(userRequest), roleType
                        )
                ));
    }



}
