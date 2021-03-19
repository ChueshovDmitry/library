package com.senla.library.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dmitry Chueshov 17.03.2021 11:41
 * @project library
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotSave extends RuntimeException{
    
    public ResourceNotSave(String message) {
        super(message);
    }
    
    
}
