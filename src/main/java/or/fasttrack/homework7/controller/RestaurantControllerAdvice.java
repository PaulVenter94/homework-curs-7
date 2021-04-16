package or.fasttrack.homework7.controller;

import lombok.Value;
import or.fasttrack.homework7.exception.ResourceNotFoundException;
import or.fasttrack.homework7.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestaurantControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ApiError handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ApiError(404, e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleRuntimeException(RuntimeException e) {
        return new ApiError(400, e.getMessage());
    }

    @Value
    private static class ApiError {
        int code;
        String message;
    }
}
