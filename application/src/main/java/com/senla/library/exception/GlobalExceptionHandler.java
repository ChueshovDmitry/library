package com.senla.library.exception;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.exception.ResourceNotSave;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

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
    
    @ExceptionHandler(ResourceDuplicationException.class)
    public ResponseEntity <?> ResourceDuplicationException(ResourceDuplicationException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage()
                ,request.getDescription(false)
                ,CONFLICT
                ,CONFLICT.value());
        return new ResponseEntity<>(errorDetails, CONFLICT);
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
    
    
    @ExceptionHandler(ResourceNotSave.class)
    public ResponseEntity<?> ResourceNotSave(ResourceNotSave exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date()
                        ,exception.getMessage()
                        ,request.getDescription(false)
                        ,    BAD_REQUEST
                        ,    BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetails,BAD_REQUEST);
    }
    
 
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> UsernameNotFoundExceptionHandling(UsernameNotFoundException exception,WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date()
                        ,exception.getMessage()
                        ,request.getDescription(false)
                        ,    NOT_FOUND
                        ,    NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails,NOT_FOUND);
    }
}

