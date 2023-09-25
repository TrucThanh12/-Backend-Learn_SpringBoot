package com.example.demotest.service;

import com.example.demotest.entity.Author;
import com.example.demotest.entity.Book;
import com.example.demotest.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AuthorService{
    private final AuthorRepository authorRepository;

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

}
