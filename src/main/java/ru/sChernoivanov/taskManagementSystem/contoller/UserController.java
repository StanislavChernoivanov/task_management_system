package ru.sChernoivanov.taskManagementSystem.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sChernoivanov.taskManagementSystem.mapping.UserMapper;
import ru.sChernoivanov.taskManagementSystem.model.entity.RoleType;
import ru.sChernoivanov.taskManagementSystem.service.UserService;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertUserRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.ErrorResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.taskResponse.TaskResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.userResponse.UserResponse;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;



    @Operation(
            summary = "Create new account",
            description = "Create new account" +
                    ", return new Account",
            tags = {"user"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = "application/json"
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("/createAcc")
    public ResponseEntity<UserResponse> createAccount(@RequestBody @Valid UpsertUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.createNewAccount(userMapper.requestToUser(userRequest))
                ));
    }



}
