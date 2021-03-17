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
    
    private int code;
    
    public ErrorDetails(Date timestamp,String message,String details,HttpStatus httpStatus,int code) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpStatus = httpStatus;
        this.code = code;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
}
