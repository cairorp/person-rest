package com.api.personrest.controller;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.model.Person;
import com.api.personrest.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class PersonControllerUnitTest {

    private PersonService personService;

    private PersonController personController;

    @BeforeEach
    void setUp() {
        personService = mock(PersonService.class);
    }

    @Test
    public void findAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(mountPerson("Cairo Pereira", "12345678910", new Date()));
        persons.add(mountPerson("Cairo Pereira", "12345678910", new Date()));
        persons.add(mountPerson("Cairo Pereira", "12345678910", new Date()));

        doReturn(ResponseEntity.ok().body(persons)).when(personService).findAll();
        personController = new PersonController(personService);

        assertEquals(persons, personController.listAll().getBody());
    }

    @Test
    public void findAllEmpty() {
        List<Person> persons = new ArrayList<>();

        doReturn(ResponseEntity.ok().body(persons)).when(personService).findAll();
        personController = new PersonController(personService);

        assertEquals(persons, personController.listAll().getBody());
    }


    @Test
    public void findById() {
        Person person = mountPerson("Cairo Pereira", "12345678910", new Date());
        doReturn(ResponseEntity.ok().body(person)).when(personService).findById(1);

        personController = new PersonController(personService);

        assertEquals(person, personController.findById(1).getBody());
    }

    @Test
    public void saveSuccess() {
        Person person = mountPerson("Junior Pereira", "79469056027", new Date());
        PersonDTO personDTO = mountPersonDTO(person.getName(),person.getDocument(),person.getBirthDate());

        doReturn(ResponseEntity.status(HttpStatus.CREATED).build()).when(personService).save(personDTO);

        personController = new PersonController(personService);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), personController.save(personDTO));
    }

    @Test
    public void validUpdate() {
        Person person = mountPerson("Angela Pereira", "79469056027", new Date());
        PersonDTO personDTO = mountPersonDTO(person.getName(),person.getDocument(),person.getBirthDate());

        doReturn(ResponseEntity.ok().build()).when(personService).update(1, personDTO);

        personController = new PersonController(personService);

        assertEquals(ResponseEntity.ok().build(), personController.update(1, personDTO));

    }

    @Test
    public void deleteExistingObject() {
        doReturn(ResponseEntity.noContent().build()).when(personService).delete(1);
        personController = new PersonController(personService);

        assertEquals(ResponseEntity.noContent().build(), personController.delete(1));
    }


    private Person mountPerson(String name, String document, Date birthDate) {
        Person person = new Person();

        person.setName(name);
        person.setDocument(document);
        person.setBirthDate(birthDate);
        person.setId(1);

        return person;
    }

    private PersonDTO mountPersonDTO(String name, String document, Date birthDate){

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setDocument(document);
        personDTO.setBirthDate(birthDate);

        return personDTO;
    }

}