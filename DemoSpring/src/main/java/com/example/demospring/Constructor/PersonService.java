package com.example.demospring.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person getPersonById(String id){
        return personRepository.findById(id).orElse(null);
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(String id){
        personRepository.deleteById(id);
    }
}
