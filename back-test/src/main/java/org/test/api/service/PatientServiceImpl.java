package org.test.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.api.model.entity.Patient;
import org.test.api.model.pojo.PatientSinglePojo;
import org.test.api.model.repository.IPatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements IPatientService {

    private final IPatientRepository repository;

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<PatientSinglePojo> getAllSingle() {
        List list = repository.findAllSingle();

        // Map list to patient single.
        List<PatientSinglePojo> listPatient = ((ArrayList<Object[]>) list).stream().map(x -> {
            PatientSinglePojo obj = new PatientSinglePojo();
            obj.setId((int) x[0]);
            obj.setFullName((String) x[1]);
            return obj;
        }).collect(Collectors.toList());

        return listPatient;
    }

    @Override
    public Optional<Patient> get(int id) {
        return repository.findById(id);
    }

    @Override
    public Patient create(Patient request) {
        repository.save(request);
        log.info("Created information for patient: {}", request);
        return request;
    }

    @Override
    public Optional<Patient> update(Patient request) {
        return Optional
                .of(repository.findById(request.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(entity -> {
                    entity.setName(request.getName());
                    entity.setLastName(request.getLastName());
                    entity.setBirthDate(request.getBirthDate());
                    entity.setAddress(request.getAddress());
                    if (request.getImage() != null) {
                        entity.setImage(request.getImage());
                    }
                    repository.save(entity);
                    log.info("Updated information for patient: {}", entity);
                    return entity;
                });
    }

    @Override
    public void delete(int id) {
        repository.findById(id)
                .ifPresent(entity -> {
                    repository.delete(entity);
                    log.info("Deleted patient: {}", entity);
                });
    }
}
