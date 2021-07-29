package org.test.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.test.api.model.entity.Patient;
import org.test.api.model.pojo.PatientSinglePojo;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    @Transactional(readOnly = true)
    Page<Patient> getAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<PatientSinglePojo> getAllSingle();

    @Transactional(readOnly = true)
    Optional<Patient> get(int id);

    @Transactional
    Patient create(Patient request);

    @Transactional
    Optional<Patient> update(Patient request);

    @Transactional
    void delete(int id);
}
