package com.api.personrest.service;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    ResponseEntity<List<Person>> listPersons();

    ResponseEntity<Person> findById(Integer id);

    ResponseEntity<Person> save(PersonDTO personDTO);

    ResponseEntity<Person> update(Integer id, PersonDTO personDTO);

    ResponseEntity<Void> delete(Integer id);
}
