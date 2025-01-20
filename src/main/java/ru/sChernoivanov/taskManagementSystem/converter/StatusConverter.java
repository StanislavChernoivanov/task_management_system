package ru.sChernoivanov.taskManagementSystem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;

public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.valueOf(source.toUpperCase());
    }
}
