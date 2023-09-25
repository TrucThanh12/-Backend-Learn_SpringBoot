package com.example.demotest.controller;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;
import com.example.demotest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BookController {
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        try{
            bookService.createBook(book);
            return ResponseEntity.ok("Create book successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/book/{page}/{size}")
    public ResponseEntity<Page<Book>> getAllBookWithPagination(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResponseEntity.ok(bookService.getAllBookWithPagination(page,size));
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/book/authorByBook/{id}")
    public ResponseEntity<Author> getAuthorByIdBook(@PathVariable("id") String id){
        Author author = bookService.getAuthorByIdBook(id);
        return ResponseEntity.ok(author);
    }


    @PutMapping("/book/orderBook/{id}/{num}")
    public void orderBook(@PathVariable("id") String id, @PathVariable("num") Integer num){
        try{
            bookService.orderBook(id,num);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id){
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok("Delete Book Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/topBook")
    public ResponseEntity<List<String>> getTopBook(){
        return ResponseEntity.ok(bookService.getTopBooks());
    }

}
