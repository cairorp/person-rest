package com.api.personrest.service;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.exception.PersonException;
import com.api.personrest.model.Person;
import com.api.personrest.repository.PersonRepository;
import com.api.personrest.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class PersonServiceTest {

    private PersonService personService;
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
    }

    @Test
    public void findAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(mountPerson("Cairo Pereira", "12345678910", new Date()));
        persons.add(mountPerson("Cairo Pereira", "12345678910", new Date()));

        doReturn(persons).when(personRepository).findAll();
        personService = new PersonServiceImpl(personRepository);

        assertEquals(ResponseEntity.ok(persons), personService.findAll());
    }

    @Test
    public void findByExistingId() {
        Person person = mountPerson("Cairo Pereira", "12345678910", new Date());

        doReturn(Optional.of(person)).when(personRepository).findById(1);
        personService = new PersonServiceImpl(personRepository);

        assertEquals(ResponseEntity.ok(person), personService.findById(1));
    }

    @Test
    public void searchForNonExistentId() {
        doReturn(Optional.empty()).when(personRepository).findById(1);
        personService = new PersonServiceImpl(personRepository);

        assertThrows(PersonException.class, () -> personService.findById(1));
    }

    @Test
    public void save() {
        Person person = mountPerson("Cairo Pereira", "12345678910", new Date());
        doReturn(person).when(personRepository).save(any());

        personService = new PersonServiceImpl(personRepository);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), personService.save(
                mountPersonDTO(person.getName(),
                        person.getDocument(),
                        person.getBirthDate())));
    }

    @Test
    public void saveWithConstraintViolationException() {
        Person person = mountPerson("Junior Pereira", "12345677910", new Date());
        doThrow(ConstraintViolationException.class).when(personRepository).save(any());

        personService = new PersonServiceImpl(personRepository);

        assertThrows(PersonException.class, () -> personService.save(mountPersonDTO(person.getName(),
                person.getDocument(),
                person.getBirthDate())));
    }

    @Test
    public void updateSuccess() {
        Person personEdit = mountPerson("Peter Pereira", "98765432178", new Date());

        doReturn(personEdit).when(personRepository).save(personEdit);
        personService = new PersonServiceImpl(personRepository);

        assertEquals(ResponseEntity.ok().build(), personService.update(1, mountPersonDTO(personEdit.getName(),
                personEdit.getDocument(),
                personEdit.getBirthDate())));
    }

    @Test
    public void updateFailed() {
        Person personEdit = mountPerson("Peter Pereira", "98765432178", new Date());

        doThrow(IllegalArgumentException.class).when(personRepository).save(any());
        personService = new PersonServiceImpl(personRepository);

        assertThrows(PersonException.class, () -> personService.update(1, mountPersonDTO(personEdit.getName(),
                personEdit.getDocument(),
                personEdit.getBirthDate())));
    }

    @Test
    public void deleteWithIdNotExisting() {
        doReturn(Optional.empty()).when(personRepository).findById(1);
        personService = new PersonServiceImpl(personRepository);

        assertThrows(PersonException.class, () -> personService.delete(1));
    }

    @Test
    public void deleteFailed() {
        Person person = mountPerson("Peter Pereira", "98765432178", new Date());
        doReturn(Optional.of(person)).when(personRepository).findById(1);
        doThrow(IllegalArgumentException.class).when(personRepository).delete(person);
        personService = new PersonServiceImpl(personRepository);

        assertThrows(PersonException.class, () -> personService.delete(1));
    }

    @Test
    public void deleteSuccess() {
        Person person = mountPerson("Peter Pereira", "98765432178", new Date());
        doReturn(Optional.of(person)).when(personRepository).findById(1);
        doNothing().when(personRepository).delete(person);
        personService = new PersonServiceImpl(personRepository);

        assertEquals(ResponseEntity.noContent().build(), personService.delete(1));
    }

    private Person mountPerson(String name, String document, Date birthDate) {
        Person person = new Person();

        person.setName(name);
        person.setDocument(document);
        person.setBirthDate(birthDate);
        person.setId(1);

        return person;
    }

    private PersonDTO mountPersonDTO(String name, String document, Date birthDate) {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setDocument(document);
        personDTO.setBirthDate(birthDate);

        return personDTO;
    }


}