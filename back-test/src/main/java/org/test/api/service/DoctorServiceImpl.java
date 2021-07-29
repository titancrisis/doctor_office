package org.test.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.api.model.entity.Doctor;
import org.test.api.model.pojo.DoctorSinglePojo;
import org.test.api.model.repository.IDoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository repository;

    @Override
    public Page<Doctor> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<DoctorSinglePojo> getAllSingle() {
        List list = repository.findAllSingle();

        // Map list to doctor single.
        List<DoctorSinglePojo> listDoctor = ((ArrayList<Object[]>) list).stream().map(x -> {
            DoctorSinglePojo obj = new DoctorSinglePojo();
            obj.setId((int) x[0]);
            obj.setFullName((String) x[1]);
            return obj;
        }).collect(Collectors.toList());

        return listDoctor;
    }

    @Override
    public Optional<Doctor> get(int id) {
        return repository.findById(id);
    }

    @Override
    public Doctor create(Doctor request) {
        repository.save(request);
        log.info("Created information for doctor: {}", request);
        return request;
    }

    @Override
    public Optional<Doctor> update(Doctor request) {
        return Optional
                .of(repository.findById(request.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(entity -> {
                    entity.setName(request.getName());
                    entity.setLastName(request.getLastName());
                    entity.setSpecialty(request.getSpecialty());
                    entity.setBirthDate(request.getBirthDate());
                    entity.setAddress(request.getAddress());
                    if (request.getImage() != null) {
                        entity.setImage(request.getImage());
                    }
                    repository.save(entity);
                    log.info("Updated information for doctor: {}", entity);
                    return entity;
                });
    }

    @Override
    public void delete(int id) {
        repository.findById(id)
                .ifPresent(entity -> {
                    repository.delete(entity);
                    log.info("Deleted doctor: {}", entity);
                });
    }
}
