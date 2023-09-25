package com.example.demotest.service;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService{
    private final BookService bookService;
    private final AuthorService authorService;

    @Override
    public List<Book> getAllBookByIdAuthor(String id) {
        List<Book> allBooks = bookService.getAllBook();
        return allBooks.stream().filter(book -> book.getIdAuthor().equals(id)).collect(Collectors.toList());    }

    @Override
    public Author getAuthorByIdBook(String id) {
        return authorService.getAuthorById(bookService.getBookById(id).getIdAuthor());
    }
}
