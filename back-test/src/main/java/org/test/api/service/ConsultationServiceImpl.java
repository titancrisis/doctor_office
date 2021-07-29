package org.test.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.api.model.entity.Consultation;
import org.test.api.model.pojo.ConsultationFullPojo;
import org.test.api.model.pojo.ConsultationHistoryPojo;
import org.test.api.model.pojo.ConsultationPatientPojo;
import org.test.api.model.repository.IConsultationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultationServiceImpl implements IConsultationService {

    private final IConsultationRepository repository;

    @Override
    public Page<Consultation> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ConsultationFullPojo> getAllFull() {

        List list = repository.findAllFull();

        // Map list to consultation full.
        List<ConsultationFullPojo> listConsultation = ((ArrayList<Object[]>) list).stream().map(x -> {
            ConsultationFullPojo obj = new ConsultationFullPojo();
            obj.setId((int) x[0]);
            obj.setPatient((String) x[1]);
            obj.setDoctor((String) x[2]);
            obj.setDescription((String) x[3]);
            obj.setCreatedAt((Date) x[4]);
            obj.setPrescriptionDrugs((String) x[5]);
            return obj;
        }).collect(Collectors.toList());

        return listConsultation;
    }

    @Override
    public Optional<Consultation> get(int id) {
        return repository.findById(id);
    }

    @Override
    public Consultation create(Consultation request) {
        request.setCreatedAt(new Date());
        repository.save(request);
        log.info("Created information for consultation: {}", request);
        return request;
    }

    @Override
    public Optional<Consultation> update(Consultation request) {
        return Optional
                .of(repository.findById(request.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(entity -> {
                    entity.setPatientId(request.getPatientId());
                    entity.setDoctorId(request.getDoctorId());
                    entity.setDescription(request.getDescription());
                    entity.setPrescriptionDrugs(request.getPrescriptionDrugs());
                    repository.save(entity);
                    log.info("Updated information for consultation: {}", entity);
                    return entity;
                });
    }

    @Override
    public void delete(int id) {
        repository.findById(id)
                .ifPresent(entity -> {
                    repository.delete(entity);
                    log.info("Deleted consultation: {}", entity);
                });
    }

    @Override
    public List<ConsultationHistoryPojo> getAllByPatientId(int patientId) {

        List list = repository.findAllByPatientId(patientId);

        List<ConsultationHistoryPojo> listDoctor = ((ArrayList<Object[]>) list).stream().map(x -> {
            ConsultationHistoryPojo obj = new ConsultationHistoryPojo();
            obj.setId((int) x[0]);
            obj.setDoctor((String) x[1]);
            obj.setSpecialty((String) x[2]);
            obj.setCreatedAt((Date) x[3]);
            obj.setPrescriptionDrugs((String) x[4]);
            obj.setDescription((String) x[5]);
            return obj;
        }).collect(Collectors.toList());

        return listDoctor;
    }

    @Override
    public List<ConsultationPatientPojo> getAllByDoctorId(int doctorId) {

        List list = repository.findAllByDoctorId(doctorId);

        List<ConsultationPatientPojo> listPatient = ((ArrayList<Object[]>) list).stream().map(x -> {
            ConsultationPatientPojo obj = new ConsultationPatientPojo();
            obj.setId((int) x[0]);
            obj.setPatient((String) x[1]);
            obj.setBirthDate((Date) x[2]);
            return obj;
        }).collect(Collectors.toList());

        return listPatient;
    }
}
