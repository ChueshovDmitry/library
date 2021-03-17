package com.senla.library.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author Dmitry Chueshov 17.03.2021 11:39
 * @project library
 */

public class ErrorDetails {
    
    private Date timestamp;
    private String message;
    private String details;
    private HttpStatus httpStatus;
    
    public ErrorDetails(Date timestamp,String message,String details,HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpStatus = httpStatus;
    }
}
