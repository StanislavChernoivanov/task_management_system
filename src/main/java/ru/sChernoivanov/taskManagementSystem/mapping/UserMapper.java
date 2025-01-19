package ru.sChernoivanov.taskManagementSystem.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.CreateUserRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.UserListResponse;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(CreateUserRequest createUserRequest);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, CreateUserRequest createUserRequest);


    UserResponse userToResponse(User user);

    List<UserResponse> userListToResponseList(List<User> users);

    default UserListResponse userListToUserResponseList(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(userListToResponseList(users));
        return userListResponse;

    }
}
