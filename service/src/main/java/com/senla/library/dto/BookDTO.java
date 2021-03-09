package com.senla.library.dto;
import lombok.Data;
import java.util.List;

@Data
public class BookDTO {
    
    private Long id;
    
    private String name;
    
    private List<AuthorDTO> authors;
}
