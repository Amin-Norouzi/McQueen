package dev.aminnorouzi.mcqueen.exception;

import org.apache.http.NoHttpResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHttpResponseException.class)
    public void handleNoHttpResponseException(NoHttpResponseException exception) {
        System.out.println(exception.getMessage());
    }


}
