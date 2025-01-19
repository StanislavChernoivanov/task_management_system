package ru.sChernoivanov.taskManagementSystem.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.sChernoivanov.taskManagementSystem.exception.AlreadyExistsException;
import ru.sChernoivanov.taskManagementSystem.exception.EntityNotFoundException;
import ru.sChernoivanov.taskManagementSystem.exception.RefreshTokenException;
import ru.sChernoivanov.taskManagementSystem.web.dto.toResponse.ErrorResponse;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        log.error("Error when trying to get entity", ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorResponse(ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        log.error("Error processing input data", ex);
        List<String> errorMessages = bindingResult.getAllErrors().
                stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).
                toList();
        String errorMessage = String.join("; ", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(errorMessage));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> notConvert(ConversionFailedException ex) {
        log.error("Ошибка при попытке конвертирования String в Enum");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse("Incorrect status or priority." +
                        "Check that the input data is correct"));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<ErrorResponse> refreshTokenException(RefreshTokenException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExistExceptionHandler(AlreadyExistsException ex) {
        log.error("Entity already exists", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(ex.getMessage()));
    }

}
