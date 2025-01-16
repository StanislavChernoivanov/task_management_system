package ru.sChernoivanov.taskManagementSystem.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sChernoivanov.taskManagementSystem.exception.EntityNotFoundException;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.ErrorResponse;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        log.error("Ошибка при попытке получить сущность", ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorResponse("Сущность не найдена, проверьте корректность входных данных"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        log.error("Ошибка при обработке входных данных", ex);
        List<String> errorMessages = bindingResult.getAllErrors().
                stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).
                toList();
        String errorMessage = String.join("; ", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(errorMessage));
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> notConvert(ConversionFailedException ex) {
        log.error("Ошибка при попытке конвертирования String в Enum", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse("Неверно указан статус или приоритет." +
                        "Проверьте корректность входных данных"));
    }
}
