package ru.sChernoivanov.taskManagementSystem.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.sChernoivanov.taskManagementSystem.converter.PriorityConverter;
import ru.sChernoivanov.taskManagementSystem.converter.StatusConverter;

@Configuration
@RequiredArgsConstructor
public class ConversionConfiguration implements WebMvcConfigurer {

    private final PriorityConverter priorityConverter;
    private final StatusConverter statusConverter;

        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(priorityConverter);
            registry.addConverter(statusConverter);
        }
}
