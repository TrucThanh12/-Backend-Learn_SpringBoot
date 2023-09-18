package com.example.demospring.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
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
