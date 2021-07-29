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
import org.test.api.model.entity.Doctor;
import org.test.api.model.pojo.DoctorPojo;
import org.test.api.model.pojo.DoctorSinglePojo;
import org.test.api.service.IDoctorService;
import org.test.api.util.PaginationUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        log.info("Request to get all doctors");

        final Page<DoctorPojo> page = doctorService.getAll(pageable).map(DoctorPojo::new);
        HttpHeaders headers = PaginationUtil.addTotalCountHttpHeaders(page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("single")
    public ResponseEntity<?> getAllSingle() {
        log.info("Request to get all single doctor");

        final List<DoctorSinglePojo> list = doctorService.getAllSingle();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id) {
        log.info("Request to get doctor : {}", id);

        Optional<Doctor> doctor = doctorService.get(id);
        if (!doctor.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder().result(true).message(ResponseMessageConstant.RESPONSE_FOUND).body(doctor.get()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Doctor request) {
        log.debug("Request to create doctor: {}", request);

        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(doctorService.create(request)).build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Doctor request) {
        log.debug("Request to update doctor: {}", request);

        Optional<Doctor> entity = doctorService.get(request.getId());
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY)
                .body(doctorService.update(request).get()).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        log.debug("Request to delete doctor: {}", id);

        Optional<Doctor> entity = doctorService.get(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(Response.builder().result(false).message(ResponseMessageConstant.RESPONSE_NOT_FOUND).build(), HttpStatus.OK);
        }
        doctorService.delete(id);
        return new ResponseEntity<>(Response.builder()
                .result(true)
                .message(ResponseMessageConstant.RESPONSE_SUCCESSFULLY).build(), HttpStatus.OK);

    }
}
