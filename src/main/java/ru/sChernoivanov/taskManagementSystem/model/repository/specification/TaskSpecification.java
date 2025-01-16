package ru.sChernoivanov.taskManagementSystem.model.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.sChernoivanov.taskManagementSystem.model.entity.Priority;
import ru.sChernoivanov.taskManagementSystem.model.entity.Status;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;

public interface TaskSpecification {


    static Specification<Task> withFilter(Status status, Priority priority, Long authorId, Long performerId) {
        return Specification.where(byStatus(status))
                .and(byPriority(priority))
                .and(byAuthorId(authorId))
                .and(byPerformerId(performerId));
    }


    static Specification<Task> byStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    static Specification<Task> byPriority(Priority priority) {
        return (root, query, criteriaBuilder) -> {
            if (priority == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("priority"), priority);
        };
    }


    static Specification<Task> byAuthorId(Long authorId) {
        return (root, query, criteriaBuilder) -> {
            if (authorId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("author").get("id"), authorId);
        };
    }

    static Specification<Task> byPerformerId(Long performerId) {
        return (root, query, criteriaBuilder) -> {
            if (performerId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("performer").get("id"), performerId);
        };
    }


}
