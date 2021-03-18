package com.senla.library.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Dmitry Chueshov 17.03.2021 11:40
 * @project library
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage()
                ,request.getDescription(false)
                ,NOT_FOUND
                ,NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date()
                        ,exception.getMessage()
                        ,request.getDescription(false)
                        ,INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }
    
    

}

