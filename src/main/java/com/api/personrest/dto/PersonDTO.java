package com.api.personrest.dto;

import com.api.personrest.model.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.util.Date;

@Setter
public class PersonDTO {
    private String name;
    private String document;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    public Person convertToPerson() {
        Person person = new Person();
        person.setName(this.name);
        person.setDocument(this.document);
        person.setBirthDate(this.birthDate);

        return person;
    }
}
