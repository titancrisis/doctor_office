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
import org.test.api.model.entity.Consultation;
import org.test.api.model.pojo.ConsultationFullPojo;
import org.test.api.model.pojo.ConsultationHistoryPojo;
import org.test.api.model.pojo.ConsultationPatientPojo;
import org.test.api.service.IConsultationService;
import org.test.api.util.PaginationUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/consultation")
public class ConsultationController {

    private final IConsultationService consultationService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        log.info("Request to get all consultations");

        final Page<Consultation> page = consultationService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.addTotalCountHttpHeaders(page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("full")
    public ResponseEntity<?> getAllFull() {
        log.info("Request to get all full consultations");

        final List<ConsultationFullPojo> list = consultationService.getAllFull();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id) {
        log.info("Request to get consultation : {}", id);

        Optional<Consultation> doctor = consultationService.get(id);
        if (!doctor.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder().result(true).message(ResponseMessageConstant.RESPONSE_FOUND).body(doctor.get()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Consultation request) {
        log.debug("Request to create consultation: {}", request);

        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(consultationService.create(request)).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Consultation request) {
        log.debug("Request to update consultation: {}", request);

        Optional<Consultation> entity = consultationService.get(request.getId());
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(consultationService.update(request).get()).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        log.debug("Request to delete consultation: {}", id);

        Optional<Consultation> entity = consultationService.get(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        consultationService.delete(id);
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY).build(), HttpStatus.OK);

    }

    @GetMapping("history/{patientId}")
    public ResponseEntity<?> getAllByPatientId(@PathVariable("patientId") int patientId) {
        log.info("Request to get all history for patient");

        final List<ConsultationHistoryPojo> list = consultationService.getAllByPatientId(patientId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("patient/{doctorId}")
    public ResponseEntity<?> getAllByDoctorId(@PathVariable("doctorId") int doctorId) {
        log.info("Request to get all patient for doctor");

        final List<ConsultationPatientPojo> list = consultationService.getAllByDoctorId(doctorId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
