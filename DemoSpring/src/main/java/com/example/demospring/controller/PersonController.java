package com.example.demospring.controller;

import com.example.demospring.model.Person;
import com.example.demospring.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<?> getAllPerson(){
        log.info("(getAllBook)");
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable String id){
        return personService.getPersonById(id);
    }

    @PostMapping("/createPerson")
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }

    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable String id){
        personService.deletePerson(id);
    }
}
