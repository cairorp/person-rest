package com.api.personrest.service.impl;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import com.api.personrest.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public ResponseEntity<List<Person>> listPersons() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<Person> findById(Integer id) {
        return ResponseEntity.ok().body(new Person());
    }

    @Override
    public ResponseEntity<Person> save(PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Person());
    }

    @Override
    public ResponseEntity<Person> update(Integer id, PersonDTO personDTO) {
        return ResponseEntity.ok().body(new Person());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return ResponseEntity.noContent().build();
    }
}
