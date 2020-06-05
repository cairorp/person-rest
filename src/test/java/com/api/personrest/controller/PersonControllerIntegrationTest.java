package com.api.personrest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/listAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAllPaginated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/listAllPaginated")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("size", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByIdExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/findById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByIdNotExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/findById/{id}", 200)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void saveValidPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"Cairo Pereira\"," +
                        "\"document\" : \"52700817095\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void savePersonWithDocumentInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"Cairo Pereira\"," +
                        "\"document\" : \"12345678910\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void savePersonWithNameEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"\"," +
                        "\"document\" : \"52700817095\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateNameAndBirthDatePersonSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/persons/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"Jorge da Capadocia\"," +
                        "\"document\" : \"69580734011\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateNameAndBirthDatePersonInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/persons/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"\"," +
                        "\"document\" : \"69580734011\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateDocumentAndBirthDatePersonInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/persons/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\" : \"Jorge Pereira\"," +
                        "\"document\" : \"12345678910\"," +
                        "\"birthDate\" : \"1995-12-15\"" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deletePersonNotExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/delete/{id}", 400)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deletePersonExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
