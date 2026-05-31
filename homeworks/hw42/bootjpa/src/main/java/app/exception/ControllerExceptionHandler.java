package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice // перехоплювати помилки з усіх контролерів
public class ControllerExceptionHandler {

    // Якщо виникла помилка "Користувача не знайдено" — повертаємо статус 404 NOT FOUND
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFound(UserNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    // помилка унікальності — повертаємо статус 400 BAD REQUEST
    @ExceptionHandler(NotUniqueUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNotUniqueUser(NotUniqueUserException ex) {
        return Map.of("error", ex.getMessage());
    }

    // помилка відкату транзакції
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneralException(RuntimeException ex) {
        return Map.of("error", ex.getMessage());
    }
}