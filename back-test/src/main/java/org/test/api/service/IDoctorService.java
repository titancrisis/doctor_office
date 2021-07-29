package org.test.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.test.api.model.entity.Doctor;
import org.test.api.model.pojo.DoctorSinglePojo;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {

    @Transactional(readOnly = true)
    Page<Doctor> getAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<DoctorSinglePojo> getAllSingle();

    @Transactional(readOnly = true)
    Optional<Doctor> get(int id);

    @Transactional
    Doctor create(Doctor request);

    @Transactional
    Optional<Doctor> update(Doctor request);

    @Transactional
    void delete(int id);
}
