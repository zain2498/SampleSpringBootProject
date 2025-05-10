package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*
* @ExceptionHandler : we use this annotation to handle the specific exception and returns the custom error response back to the client.
@ControllerAdvice : we use this annotation to handle the exception globally it means that we use to handle all the specific exceptions and global exceptions in a  single place.
* */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException userException, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                userException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleEmailALreadyExistsException(EmailAlreadyExistException emailException, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                emailException.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ADDRESS_ALREADY_EXIST"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
