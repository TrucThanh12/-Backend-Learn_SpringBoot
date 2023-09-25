package com.example.demotest.controller;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;
import com.example.demotest.service.AuthorService;
import com.example.demotest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AuthorController {
    private AuthorService authorService;
    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<String> createAuthor(@RequestBody Author author){
        try{
            authorService.createAuthor(author);
            return ResponseEntity.ok("Create author successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthor(){
        List<Author> authors = authorService.getAllAuthor();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/author/bookByAuthor/{id}")
    public ResponseEntity<List<Book>> getBookByIdAuthor(@PathVariable("id") String id){
        List<Book> books = authorService.getAllBookByIdAuthor(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id){
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/delete/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") String id){
        try{
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("Delete author Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
