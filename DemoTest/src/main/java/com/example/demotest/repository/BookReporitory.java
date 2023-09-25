package com.example.demotest.repository;

import com.example.demotest.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReporitory extends MongoRepository<Book, String> {
    @Override
    Page<Book> findAll(Pageable pageable);
}
