package com.example.demospring.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(String id){
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }
}
