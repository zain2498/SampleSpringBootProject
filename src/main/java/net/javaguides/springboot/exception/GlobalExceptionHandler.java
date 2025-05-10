package net.javaguides.springboot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/*
* @ExceptionHandler : we use this annotation to handle the specific exception and returns the custom error response back to the client.
@ControllerAdvice : we use this annotation to handle the exception globally it means that we use to handle all the specific exceptions and global exceptions in a  single place.
* */
@ControllerAdvice
public class GlobalExceptionHandler {


}
