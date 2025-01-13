package ru.sChernoivanov.taskManagementSystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sChernoivanov.taskManagementSystem.model.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
