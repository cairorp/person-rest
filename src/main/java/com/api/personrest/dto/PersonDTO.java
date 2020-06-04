package com.api.personrest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PersonDTO {
    private String name;
    private String document;
    private Date birthDate;
}
