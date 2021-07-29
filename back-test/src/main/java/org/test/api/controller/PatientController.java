package org.test.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.api.comun.constant.ResponseMessageConstant;
import org.test.api.comun.pojo.Response;
import org.test.api.model.entity.Patient;
import org.test.api.model.pojo.PatientPojo;
import org.test.api.model.pojo.PatientSinglePojo;
import org.test.api.service.IPatientService;
import org.test.api.util.PaginationUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/patient")
public class PatientController {

    private final IPatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        log.info("Request to get all patients");

        final Page<PatientPojo> page = patientService.getAll(pageable).map(PatientPojo::new);
        HttpHeaders headers = PaginationUtil.addTotalCountHttpHeaders(page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("single")
    public ResponseEntity<?> getAllSingle() {
        log.info("Request to get all single patient");

        final List<PatientSinglePojo> list = patientService.getAllSingle();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id) {
        log.info("Request to get patient : {}", id);

        Optional<Patient> patient = patientService.get(id);
        if (!patient.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder().result(true).message(ResponseMessageConstant.RESPONSE_FOUND).body(patient.get()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Patient request) {
        log.debug("Request to create patient: {}", request);

        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(patientService.create(request)).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Patient request) {
        log.debug("Request to update patient: {}", request);

        Optional<Patient> entity = patientService.get(request.getId());
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(patientService.update(request).get()).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        log.debug("Request to delete patient: {}", id);

        Optional<Patient> entity = patientService.get(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        patientService.delete(id);
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY).build(), HttpStatus.OK);

    }
}
