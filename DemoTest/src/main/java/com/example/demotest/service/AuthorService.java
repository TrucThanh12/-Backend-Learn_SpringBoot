package com.example.demotest.service;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;
import com.example.demotest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AuthorService{

    private AuthorRepository authorRepository;
    private BookService bookService;
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }
    public void createAuthor(Author author){
        authorRepository.save(author);
    }
    public Author getAuthorById(String id){
        Optional<Author> result = authorRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            return null;
        }
    }
    public void deleteAuthor(String id){
        authorRepository.deleteById(id);
    }

    public List<Book> getAllBookByIdAuthor(String id) {
        List<Book> allBooks = bookService.getAllBook();
        return allBooks.stream().filter(book -> book.getIdAuthor().equals(id)).collect(Collectors.toList());
    }

}
