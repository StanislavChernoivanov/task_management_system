package ru.sChernoivanov.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.sChernoivanov.taskManagementSystem.model.entity.Task;
import ru.sChernoivanov.taskManagementSystem.service.TaskService;
import ru.sChernoivanov.taskManagementSystem.service.UserService;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessToOperationAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    @Pointcut("@annotation(ru.sChernoivanov.aop.CheckAccess)")
    public void callAtAllMethods() {

    }


    @Before("callAtAllMethods()")
    public void userAccessChecking(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        UserDetails userDetails = null;
        Long taskId = null;

        for (Object arg : arguments) {
            if (arg instanceof UserDetails) {
                userDetails = (UserDetails) arg;
            }
            if (arg instanceof Long) {
                taskId = (Long) arg;
            }
        }

        Task task = taskService.findById(taskId);

        assert userDetails != null;
        userService.checkAccessByUser(userDetails.getUsername(), task);

    }
}
