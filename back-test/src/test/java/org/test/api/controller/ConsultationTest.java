package org.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.test.api.model.entity.Consultation;
import org.test.api.util.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ConsultationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc
                .perform(get("/api/consultation")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getAllFull() throws Exception {
        mockMvc
                .perform(get("/api/consultation/full")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getAllHistory() throws Exception {
        mockMvc
                .perform(get("/api/consultation/history/{patientId}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getAllPatient() throws Exception {
        mockMvc
                .perform(get("/api/consultation/patient/{doctorId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void getOne() throws Exception {
        mockMvc
                .perform(get("/api/consultation/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.id").value(1));
    }

    @Test
    void create() throws Exception {

        Consultation entity = new Consultation();
        entity.setPatientId(1);
        entity.setDoctorId(1);
        entity.setPrescriptionDrugs("PASTILLAS");
        entity.setDescription("Revisión mensual");

        mockMvc
                .perform(post("/api/consultation")
                        .content(TestUtil.asJsonString(entity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body.id").exists());
    }

    @Test
    void update() throws Exception {

        Consultation entity = new Consultation();
        entity.setId(10);
        entity.setPatientId(1);
        entity.setDoctorId(1);
        entity.setPrescriptionDrugs("PASTILLAS, JARABE");
        entity.setDescription("Revisión mensual - Resfrio");

        mockMvc
                .perform(put("/api/consultation")
                        .content(TestUtil.asJsonString(entity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.id").value(10))
                .andExpect(jsonPath("$.body.prescriptionDrugs").value("PASTILLAS, JARABE"))
                .andExpect(jsonPath("$.body.description").value("Revisión mensual - Resfrio"));
    }

    @Test
    void deleteOne() throws Exception {

        mockMvc
                .perform(delete("/api/consultation/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
