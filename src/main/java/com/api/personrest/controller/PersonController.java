package com.api.personrest.controller;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import com.api.personrest.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "Controller reponsável por manter a entidade Pessoa.")
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation("Lista todas as pessoas registradas.")
    @GetMapping("/listAll")
    public ResponseEntity<List<Person>> findAll() {
        return personService.findAll();
    }

    @ApiOperation("Lista todas as pessoas registradas de forma paginada.")
    @GetMapping("/listAllPaginated")
    public ResponseEntity<Page<Person>> findAll(@RequestParam Integer page,
                                                @RequestParam Integer size) {
        return personService.findAll(page, size);
    }


    @ApiOperation("Busca uma pessoa a partir do ID.")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Person> findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @ApiOperation("Grava os dados de uma pessoa.")
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody PersonDTO personDTO) {
        return personService.save(personDTO);
    }

    @ApiOperation("Atualiza dados de uma pessoa.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @RequestBody PersonDTO personDTO) {
        return personService.update(id, personDTO);
    }

    @ApiOperation("Exclui os dados de uma pessoa a partir do ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return personService.delete(id);
    }


}
