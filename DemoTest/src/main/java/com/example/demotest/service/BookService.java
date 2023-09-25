package com.example.demotest.service;
import com.example.demotest.entity.Book;
import com.example.demotest.exception.BookNotFoundException;
import com.example.demotest.repository.BookReporitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookReporitory bookReporitory;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public Page<Book> getAllBookWithPagination(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        return bookReporitory.findAll(pageRequest);
    }

    public List<Book> getAllBook(){
        return bookReporitory.findAll();
    }

    public List<String> getTopBooks(){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> topBooks = zSetOperations.reverseRange("topBooks",0,-1);
        return topBooks.stream().collect(Collectors.toList());
    }
    public void createBook(Book book){
        bookReporitory.save(book);
    }

    public Book getBookById(String id){
        return bookReporitory.findById(id).orElse(null);
    }

    public void orderBook(String id, Integer num){
        Book book = bookReporitory.findById(id).orElse(null);
        if(book!=null){
            int newPurchase = book.getPurchased()+num;
            book.setPurchased(newPurchase);
            bookReporitory.save(book);
            ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
            zSetOperations.add("topBooks",id,newPurchase);

            //loại bỏ sách khỏi top sách bán chạy
            long topBooksCount  = zSetOperations.zCard("topBooks");
            if(topBooksCount> 5){
                long excessBooks = topBooksCount - 5;
                zSetOperations.removeRange("topBooks",0, excessBooks-1);
            }

        }
        else{
            throw new BookNotFoundException("Book with ID" + id+ "not found");
        }
    }
    public void deleteBook(String id){
        bookReporitory.deleteById(id);
    }


}
