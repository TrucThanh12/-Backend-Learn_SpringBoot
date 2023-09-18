package com.example.demospring.Setter;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@Data
public class Book {
    @Id
    private String id;
    private String name;
    private Integer price;
}
