package com.senla.library.entity;

/**
 * @author Dmitry Chueshov 07.12.2020 19:18
 * @project library
 */

public enum BookStatus {
            RENT("The book is rent "),
            FREE("The book is free");
    
   private String statusBook;
    
    BookStatus(String statusBook) {
        this.statusBook = statusBook;
    }
    
    public String getStatusBook() {
        return statusBook;
    }
}
