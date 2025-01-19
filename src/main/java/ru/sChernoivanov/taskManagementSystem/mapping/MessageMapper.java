package ru.sChernoivanov.taskManagementSystem.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;
import ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.UpsertMessageRequest;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.MessageResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {


    Message requestToMessage(UpsertMessageRequest upsertMessageRequest);

    @Mapping(source = "messageId", target = "id")
    Message requestToMessage(Long messageId, UpsertMessageRequest upsertMessageRequest);


    MessageResponse messageToResponse(Message message);
}
