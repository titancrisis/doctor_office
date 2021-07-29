package org.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.test.api.model.entity.Patient;
import org.test.api.util.TestUtil;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PatientTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc
                .perform(get("/api/patient")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getAllSingle() throws Exception {
        mockMvc
                .perform(get("/api/patient/single")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getOne() throws Exception {
        mockMvc
                .perform(get("/api/patient/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.id").value(1));
    }

    @Test
    void create() throws Exception {

        Patient entity = new Patient();
        entity.setName("JUAN");
        entity.setLastName("PEREZ");
        entity.setBirthDate(new Date(1990, 2, 15));
        entity.setAddress("Av. 123");
        entity.setImage("abc");

        mockMvc
                .perform(post("/api/patient")
                        .content(TestUtil.asJsonString(entity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body.id").exists());
    }

    @Test
    void update() throws Exception {

        Patient entity = new Patient();
        entity.setId(10);
        entity.setName("JUAN CARLOS");
        entity.setLastName("CAMACHO");
        entity.setBirthDate(new Date(2000, 2, 15));
        entity.setAddress("Av. Nro. 123");
        entity.setImage("qwe");

        mockMvc
                .perform(put("/api/patient")
                        .content(TestUtil.asJsonString(entity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.id").value(10))
                .andExpect(jsonPath("$.body.name").value("JUAN CARLOS"))
                .andExpect(jsonPath("$.body.lastName").value("CAMACHO"));
    }

    @Test
    void deleteOne() throws Exception {

        mockMvc
                .perform(delete("/api/patient/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
