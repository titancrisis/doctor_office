package org.test.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.test.api.model.entity.Consultation;
import org.test.api.model.pojo.ConsultationFullPojo;
import org.test.api.model.pojo.ConsultationHistoryPojo;
import org.test.api.model.pojo.ConsultationPatientPojo;

import java.util.List;
import java.util.Optional;

public interface IConsultationService {

    @Transactional(readOnly = true)
    Page<Consultation> getAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<ConsultationFullPojo> getAllFull();

    @Transactional(readOnly = true)
    Optional<Consultation> get(int id);

    @Transactional
    Consultation create(Consultation request);

    @Transactional
    Optional<Consultation> update(Consultation request);

    @Transactional
    void delete(int id);

    @Transactional
    List<ConsultationHistoryPojo> getAllByPatientId(int patientId);

    @Transactional
    List<ConsultationPatientPojo> getAllByDoctorId(int doctorId);
}
