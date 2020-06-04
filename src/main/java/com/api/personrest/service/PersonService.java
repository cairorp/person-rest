package com.api.personrest.service;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    ResponseEntity<List<Person>> findAll();

    ResponseEntity<Person> findById(Integer id);

    ResponseEntity<Void> save(PersonDTO personDTO);

    ResponseEntity<Void> update(Integer id, PersonDTO personDTO);

    ResponseEntity<Void> delete(Integer id);
}
