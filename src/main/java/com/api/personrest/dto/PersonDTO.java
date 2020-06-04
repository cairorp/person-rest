package com.api.personrest.dto;

import com.api.personrest.model.Person;
import lombok.Setter;

import java.util.Date;

@Setter
public class PersonDTO {
    private String name;
    private String document;
    private Date birthDate;

    public Person convertToPerson() {
        Person person = new Person();
        person.setName(this.name);
        person.setDocument(this.document);
        person.setBirthDate(this.birthDate);

        return person;
    }
}
