package com.api.personrest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "PESSOA")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @NotBlank(message = "O campo nome deve ser preenchido.")
    @Column(name = "NOME")
    private String name;

    @NotBlank(message = "O campo documento deve ser preenchido.")
    @CPF(message = "CPF inválido.")
    @Column(name = "CPF")
    private String document;


    @NotNull(message = "O campo data de nascimento deve ser preenchido.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "DATA_NASCIMENTO")
    private Date birthDate;

}