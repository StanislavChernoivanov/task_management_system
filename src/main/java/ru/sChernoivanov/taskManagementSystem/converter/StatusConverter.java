package ru.sChernoivanov.taskManagementSystem.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;

@Component
public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.valueOf(source.toUpperCase());
    }
}
