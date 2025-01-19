package ru.sChernoivanov.taskManagementSystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sChernoivanov.taskManagementSystem.model.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
