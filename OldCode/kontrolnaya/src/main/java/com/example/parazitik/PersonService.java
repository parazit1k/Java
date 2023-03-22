package com.example.parazitik;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.getOne(id);
    }

    public Person addPerson(Person person) { return personRepository.saveAndFlush(person); }

    public Person updatePerson(Person person) {
        return personRepository.saveAndFlush(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }
}
