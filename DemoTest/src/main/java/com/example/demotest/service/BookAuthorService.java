package com.example.demotest.service;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;

import java.util.List;
public interface BookAuthorService {
    List<Book> getAllBookByIdAuthor(String id);
    Author getAuthorByIdBook(String id);

}
