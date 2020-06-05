package com.api.personrest.service.impl;

import com.api.personrest.dto.PersonDTO;
import com.api.personrest.exception.PersonException;
import com.api.personrest.model.Person;
import com.api.personrest.repository.PersonRepository;
import com.api.personrest.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    @Override
    public ResponseEntity<Person> findById(Integer id) {
        Optional<Person> person = personRepository.findById(id);

        return ResponseEntity.ok(person.orElseThrow(() ->
                new PersonException("Pessoa não cadastrada! ")));
    }

    @Override
    public ResponseEntity<Void> save(PersonDTO personDTO) {
        try {
            Person person = personDTO.convertToPerson();
            personRepository.save(person);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConstraintViolationException | IllegalArgumentException ex) {
            throw new PersonException("Erro ao gravar dados de pessoa.", ex);
        }
    }

    @Override
    public ResponseEntity<Void> update(Integer id, PersonDTO personDTO) {
        try {
            Person person = personDTO.convertToPerson();
            person.setId(id);

            personRepository.save(person);

            return ResponseEntity.ok().build();
        } catch (ConstraintViolationException | IllegalArgumentException ex) {
            throw new PersonException("Erro ao atualizar dados de pessoa.");
        }
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        Person person = findById(id).getBody();

        try {
            personRepository.delete(Objects.requireNonNull(person));

            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException ex) {
            throw new PersonException("Não foi possivel deletar a pessoa de ID: " + id);
        }
    }

}
