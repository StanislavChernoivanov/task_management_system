package ru.sChernoivanov.taskManagementSystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {


    @Bean
    public OpenAPI openAPIDescription() {
        io.swagger.v3.oas.models.servers.Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");

        io.swagger.v3.oas.models.info.Contact contact = new Contact();
        contact.setEmail("StanislavChernoivanov@yandex.ru");
        contact.setName("Stanislav Chernoivanov");
        contact.setUrl("https://t.me/StanislavChern");

        Info info = new Info()
                .title("Task management system API")
                .description("API for users to create tasks, manage them, with the ability to leave comments")
                .contact(contact)
                .version("1.0");

        return new OpenAPI().info(info).servers(List.of(localhostServer));
    }


}
