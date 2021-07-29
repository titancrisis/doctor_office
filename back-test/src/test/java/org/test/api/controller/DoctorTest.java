package org.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.test.api.model.entity.Doctor;
import org.test.api.util.TestUtil;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class DoctorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc
                .perform(get("/api/doctor")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getAllSingle() throws Exception {
        mockMvc
                .perform(get("/api/doctor/single")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getOne() throws Exception {
        mockMvc
                .perform(get("/api/doctor/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.id").value(1));
    }

    @Test
    void create() throws Exception {

        Doctor entity = new Doctor();
        entity.setName("DANIEL");
        entity.setLastName("QUINTEROS");
        entity.setBirthDate(new Date(1986, 10, 15));
        entity.setSpecialty("CARDIOLOGO");
        entity.setAddress("Call. 123");
        entity.setImage("xyz");

        mockMvc
                .perform(post("/api/doctor")
                        .content(TestUtil.asJsonString(entity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body.id").exists());
    }

    @Test
    void update() throws Exception {

        Doctor entity = new Doctor();
        entity.setId(10);
        entity.setName("MARCO ANTONIO");
        entity.setLastName("CAMACHO");
        entity.setBirthDate(new Date(2000, 2, 15));
        entity.setSpecialty("TRAUMATOLOGO");
        entity.setAddress("Av. Nro. 123");
        entity.setImage("qwe");

        mockMvc
                .perform(put("/api/doctor")
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
                .perform(delete("/api/doctor/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
