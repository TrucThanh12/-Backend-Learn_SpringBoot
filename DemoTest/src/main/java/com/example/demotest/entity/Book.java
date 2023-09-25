package com.example.demotest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class Book implements Serializable {
    @Id
    private String id;
    private String name;
    private Integer price;
    private Integer purchased = 0;
    private String idAuthor;
}
