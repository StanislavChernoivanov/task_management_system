package ru.sChernoivanov.taskManagementSystem.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;


public class PriorityConverter implements Converter<String, Priority> {
    @Override
    public Priority convert(String source) {
        return Priority.valueOf(source.toUpperCase());
    }
}
