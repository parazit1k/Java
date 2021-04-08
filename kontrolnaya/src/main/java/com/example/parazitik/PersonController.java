package com.example.parazitik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository PersonRepository;

    private final PersonService PersonService = new PersonService(PersonRepository);

    @GetMapping("/person")
    public List<Person> getPersons() {
        return PersonService.getAllPersons();
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return PersonService.addPerson(person);
    }

    @PutMapping("/person/{personId}")
    public Person updatePerson(@PathVariable Long personId, @RequestBody Person PersonRequest) {
        Person person = PersonService.getById(personId);
        person.setLogin(PersonRequest.getLogin());
        person.setName(PersonRequest.getName());
        person.setLastname(PersonRequest.getLastname());
        person.setDate(PersonRequest.getDate());
        person.setFaname(PersonRequest.getFaname());
        return PersonService.updatePerson(person);
    }

    @DeleteMapping("person/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        PersonService.deletePerson(PersonService.getById(personId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
