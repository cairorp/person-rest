package com.api.personrest.controller;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import com.api.personrest.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/listarPessoas")
    public ResponseEntity<List<Person>> findAll() {
        return personService.findAll();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Person> findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody PersonDTO personDTO) {
        return personService.save(personDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                         @RequestBody PersonDTO personDTO) {
        return personService.update(id, personDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return personService.delete(id);
    }


}