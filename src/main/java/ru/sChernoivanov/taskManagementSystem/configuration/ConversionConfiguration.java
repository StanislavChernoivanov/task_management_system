package ru.sChernoivanov.taskManagementSystem.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.sChernoivanov.taskManagementSystem.converter.PriorityConverter;
import ru.sChernoivanov.taskManagementSystem.converter.StatusConverter;

@Configuration
public class ConversionConfiguration implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PriorityConverter());
        registry.addConverter(new StatusConverter());
    }
}
